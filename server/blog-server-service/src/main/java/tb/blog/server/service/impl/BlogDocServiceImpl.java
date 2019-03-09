package tb.blog.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tb.blog.server.data.dao.BlogDocDao;
import tb.blog.server.data.entity.BlogDocEntity;
import tb.blog.server.service.IBlogDocService;

/**
 * @author TB
 */
@Service("blogDocService")
public class BlogDocServiceImpl extends ServiceImpl<BlogDocDao, BlogDocEntity> implements IBlogDocService {
}
