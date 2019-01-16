package tb.blog.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tb.blog.server.data.dao.BlogVersionDao;
import tb.blog.server.data.entity.BlogVersionEntity;
import tb.blog.server.service.IBlogVersionService;

/**
 * @author TB
 */
@Service("blogVersionService")
public class BlogVersionServiceImpl extends ServiceImpl<BlogVersionDao, BlogVersionEntity> implements IBlogVersionService {
}
