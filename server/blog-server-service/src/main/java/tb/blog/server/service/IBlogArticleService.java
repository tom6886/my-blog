package tb.blog.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import tb.blog.server.data.entity.BlogArticleEntity;

import java.util.Map;

/**
 * @author TB
 */
public interface IBlogArticleService extends IService<BlogArticleEntity> {
    /**
     * 获取文章分页列表
     *
     * @param params
     * @return
     */
    Page<Map<String, Object>> queryPage(Map<String, Object> params);
}
