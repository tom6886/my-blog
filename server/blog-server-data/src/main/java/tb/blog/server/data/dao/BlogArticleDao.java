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
    List<Map<String, Object>> queryPage(@Param("page") Page page, @Param("params") Map<String, Object> params);
}
