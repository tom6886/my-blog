package tb.blog.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tb.blog.server.data.dao.SysUserDao;
import tb.blog.server.data.entity.SysUserEntity;
import tb.blog.server.service.ISysUserService;

/**
 * @author TB
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements ISysUserService {
}
