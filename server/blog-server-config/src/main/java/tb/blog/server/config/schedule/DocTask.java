package tb.blog.server.config.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tb.blog.server.service.IBlogDocService;


/**
 * 定时任务 清理无用的文件
 *
 * @author tangbo
 */
@Component
public class DocTask {

    private final IBlogDocService blogDocService;

    @Autowired
    public DocTask(IBlogDocService blogDocService) {
        this.blogDocService = blogDocService;
    }

    /**
     * 每天凌晨3点执行一次
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void clearDb() {
        blogDocService.deleteDbExtra();
    }

    /**
     * 每周星期六凌晨4点执行一次
     */
    @Scheduled(cron = "0 0 4 ? * 1")
    public void clearYun() {
        blogDocService.deleteQiNiuExtra();
    }
}
