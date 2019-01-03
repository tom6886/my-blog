package tb.blog.server.config.jwt;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;
import tb.blog.server.config.util.R;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author TB
 * JWT鉴权
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        R res = new R();

        try {
            if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
                res.setMsg("未找到 token");
                return;
            }
            // 如果请求头中有token，则进行解析，并且设置认证信息
            UsernamePasswordAuthenticationToken token = getAuthentication(tokenHeader);
            if (token == null) {
                res.setMsg("token 已失效");
                return;
            }

            SecurityContextHolder.getContext().setAuthentication(token);
            super.doFilterInternal(request, response, chain);
        } finally {
            if (!StringUtils.isEmpty(res.getMsg())) {
                response.setCharacterEncoding("utf-8");
                res.setCode(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write(JSON.toJSONString(res));
            }
        }
    }

    /**
     * 从token中获取用户信息并新建一个token
     *
     * @param tokenHeader
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String username = JwtTokenUtils.getUsername(token);
        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
        }
        return null;
    }
}
