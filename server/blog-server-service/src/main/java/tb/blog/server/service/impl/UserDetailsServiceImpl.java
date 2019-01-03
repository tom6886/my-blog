package tb.blog.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tb.blog.server.data.entity.JwtUserEntity;
import tb.blog.server.data.entity.SysUserEntity;
import tb.blog.server.service.ISysUserService;

/**
 * spring-security UserDetailsService接口的实现类
 * @author TB
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ISysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new JwtUserEntity(sysUserService.getOne(new QueryWrapper<SysUserEntity>().eq("account", username)));
    }
}
