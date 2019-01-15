package tb.blog.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tb.blog.server.data.dao.BlogArticleDao;
import tb.blog.server.data.entity.BlogArticleEntity;
import tb.blog.server.service.IBlogArticleService;

/**
 * @author TB
 */
@Service("blogArticleService")
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleDao, BlogArticleEntity> implements IBlogArticleService {
}
