package tb.blog.server.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tb.blog.server.api.util.BCryptUtil;
import tb.blog.server.api.util.R;
import tb.blog.server.config.jwt.JwtTokenUtils;
import tb.blog.server.data.entity.SysUserEntity;
import tb.blog.server.service.ISysUserService;

import java.util.Map;

/**
 * @author tangbo
 */
@RestController
@RequestMapping("/")
public class LoginController {
    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private BCryptUtil bCryptUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public R login(@RequestBody Map<String, Object> params) {
        String username = params.get("username").toString();
        String password = params.get("password").toString();
        SysUserEntity user = sysUserService.getOne(new QueryWrapper<SysUserEntity>().eq("account", username));

        if (user == null) {
            return R.error("账号或密码错误");
        }

        if (!bCryptUtil.matches(password, user.getPassword())) {
            return R.error("密码错误");
        }

        String token = JwtTokenUtils.createToken(username, false);
        return R.ok(JwtTokenUtils.TOKEN_PREFIX + token);
    }
}
