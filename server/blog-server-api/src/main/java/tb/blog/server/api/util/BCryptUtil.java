package tb.blog.server.api.util;//package tb.blog.server.common.util;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BCryptUtil {
//
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public BCryptUtil() {
//        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
//    }
//
//    /**
//     * 加密
//     *
//     * @return
//     */
//    public String encoder(String password) {
//        return bCryptPasswordEncoder.encode(password);
//    }
//
//    /**
//     * 验证密码
//     *
//     * @param password
//     * @param salt
//     * @return
//     */
//    public Boolean matches(String password, String salt) {
//        return bCryptPasswordEncoder.matches(password, salt);
//    }
//
//}
//
