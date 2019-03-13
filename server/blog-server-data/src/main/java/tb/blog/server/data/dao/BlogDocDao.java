package tb.blog.server.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import tb.blog.server.data.entity.BlogDocEntity;

import java.util.List;

/**
 * @author TB
 */
public interface BlogDocDao extends BaseMapper<BlogDocEntity> {

    /**
     * 查询所有无对应文章的文件的实体
     *
     * @return 文件列表
     */
    List<BlogDocEntity> queryExtra();


    /**
     * 查询无对应文章且未被其他文章公用的文件的 name
     *
     * @return name列表
     */
    List<String> queryNoRepeatExtra();

    /**
     * 查询表中所有不重复的文件名
     *
     * @return name列表
     */
    List<String> queryAllKeys();
}
