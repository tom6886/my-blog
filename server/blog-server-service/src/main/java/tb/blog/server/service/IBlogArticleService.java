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
     * @param params 参数
     * @return 分页数据
     */
    Page<Map<String, Object>> queryPage(Map<String, Object> params);

    /**
     * 根据id删除文章
     *
     * @param id 文章id
     * @return 删除行数
     */
    int deleteById(Long id);
}
