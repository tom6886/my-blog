package tb.blog.server.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import tb.blog.server.data.entity.BlogArticleEntity;

import java.util.List;
import java.util.Map;

/**
 * @author TB
 */
public interface BlogArticleDao extends BaseMapper<BlogArticleEntity> {
    /**
     * 分页查询文章列表
     *
     * @param page   分页对象
     * @param params 查询参数
     * @return 分页文章列表
     */
    List<Map<String, Object>> queryPage(@Param("page") Page page, @Param("params") Map<String, Object> params);
}
