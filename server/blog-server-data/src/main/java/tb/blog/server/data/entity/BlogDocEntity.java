package tb.blog.server.data.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author TB
 */
@TableName("blog_doc")
@Data
public class BlogDocEntity {
    private Long id;

    private String name;

    private String path;

    private Long uploadBy;

    private Date uploadTime;

    private String articleId;
}
