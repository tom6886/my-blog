package tb.blog.server.data.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author TB
 */
@TableName("sys_user")
@Data
public class BaseEntity {
    private Long id;

    private Date createTime;

    private Long createBy;
}
