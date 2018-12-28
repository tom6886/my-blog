package tb.blog.server.data.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("sys_user")
@Data
public class SysUserEntity {
    private Long id;

    private Date createTime;

    private Long createBy;

    private String account;

    private String password;
}
