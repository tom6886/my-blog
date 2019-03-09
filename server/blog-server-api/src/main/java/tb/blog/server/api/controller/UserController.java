package tb.blog.server.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tb.blog.server.api.util.BCryptUtil;
import tb.blog.server.api.util.R;
import tb.blog.server.data.entity.SysUserEntity;
import tb.blog.server.service.ISysUserService;

import java.util.Map;

/**
 * @author TB
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final ISysUserService sysUserService;

    private final BCryptUtil bCryptUtil;

    @Autowired
    public UserController(ISysUserService sysUserService, BCryptUtil bCryptUtil) {
        this.sysUserService = sysUserService;
        this.bCryptUtil = bCryptUtil;
    }

    @RequestMapping(value = "/changePwd", method = RequestMethod.POST, consumes = "application/json")
    public R changePwd(@RequestBody Map<String, Object> params) {
        String origin = params.get("origin").toString();
        String newone = params.get("newone").toString();

        Long userId = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        SysUserEntity user = sysUserService.getOne(new QueryWrapper<SysUserEntity>().eq("id", userId));

        if (!bCryptUtil.matches(origin, user.getPassword())) {
            return R.error("原密码错误");
        }

        user.setPassword(bCryptUtil.encoder(newone));

        return sysUserService.updateById(user) ? R.ok() : R.error("保存失败");
    }
}
