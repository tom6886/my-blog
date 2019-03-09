package tb.blog.server.config.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import tb.blog.server.config.util.UserContext;

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
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            // 如果请求头中有token，则进行解析，并且设置认证信息，同时刷新token
            String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");

            String username = JwtTokenUtils.getUsername(token);
            if (username == null) {
                SecurityContextHolder.getContext().setAuthentication(null);
                return;
            }

            UserContext.set(Long.valueOf(username));
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>()));
            response.setHeader("Access-Control-Expose-Headers", JwtTokenUtils.TOKEN_HEADER);
            response.setHeader(JwtTokenUtils.TOKEN_HEADER, JwtTokenUtils.TOKEN_PREFIX + JwtTokenUtils.createToken(username, false));
        } catch (Exception e) {
            SecurityContextHolder.getContext().setAuthentication(null);
        } finally {
            super.doFilterInternal(request, response, chain);
        }


    }
}
