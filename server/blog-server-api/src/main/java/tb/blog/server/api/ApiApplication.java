package tb.blog.server.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author tangbo
 */
@SpringBootApplication
@ComponentScan(basePackages = {"tb.blog.server.api", "tb.blog.server.config", "tb.blog.server.service.*", "tb.blog.server.data.config"})
@MapperScan(basePackages = "tb.blog.server.data.dao")
@EnableScheduling
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}

