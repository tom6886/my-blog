package tb.blog.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import tb.blog.server.data.entity.BlogDocEntity;

/**
 * @author TB
 */
public interface IBlogDocService extends IService<BlogDocEntity> {

    /**
     * 清理数据库中多余的文件记录
     */
    void deleteDbExtra();

    /**
     * 清理七牛云上多余的图片
     */
    void deleteQiNiuExtra();

    /**
     * 根据文章id删除对应的文件
     *
     * @param pageId 文章的page_id
     * @return 删除行数
     */
    int deleteByArticleId(String pageId);
}
