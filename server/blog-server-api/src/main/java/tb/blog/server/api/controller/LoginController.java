package tb.blog.server.api.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tb.blog.server.api.util.R;

import java.util.Map;

/**
 * @author tangbo
 */
@RestController
@RequestMapping("/")
public class LoginController {
    //    @Autowired
//    private ISysUserService sysUserService;
//
////    @Autowired
////    private BCryptUtil bCryptUtil;
//
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public R login(@RequestBody Map<String, Object> params) {
//    String username = params.get("username").toString();
//    String password = params.get("password").toString();
////        String password = bCryptUtil.encoder(params.get("password").toString());
//    SysUserEntity user = sysUserService.getOne(new QueryWrapper<SysUserEntity>().eq("account", username).eq("password", password));
//    return R.ok(user);
        return R.ok();
    }
}
