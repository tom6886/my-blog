package tb.blog.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tb.blog.server.data.dao.BlogArticleDao;
import tb.blog.server.data.entity.BlogArticleEntity;
import tb.blog.server.service.IBlogArticleService;

import java.util.Map;

/**
 * @author TB
 */
@Service("blogArticleService")
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleDao, BlogArticleEntity> implements IBlogArticleService {
    @Override
    public Page<Map<String, Object>> queryPage(Map<String, Object> params) {
        Page<Map<String, Object>> page = new Page<>(Integer.valueOf(params.get("page").toString()), Integer.valueOf(params.get("limit").toString()));
        page.setRecords(this.baseMapper.queryPage(page, params));
        return page;
    }
}
