package tb.blog.server.data.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author TB
 */
@TableName("blog_version")
@Data
public class BlogVersionEntity extends BaseEntity {
    private String version;

    private String content;
}
