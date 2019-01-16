package tb.blog.server.data.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author TB
 */
@TableName("blog_article")
@Data
public class BlogArticleEntity extends BaseEntity {
    private String title;

    private String des;

    private Date publishTime;

    private Integer classify;

    private String content;
}
