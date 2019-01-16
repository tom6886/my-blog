package tb.blog.server.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tb.blog.server.api.util.R;
import tb.blog.server.config.annotation.AutoModel;
import tb.blog.server.data.entity.BlogVersionEntity;
import tb.blog.server.service.IBlogVersionService;

/**
 * @author TB
 */
@RestController
@RequestMapping("/version")
public class VersionController {

    @Autowired
    private IBlogVersionService blogVersionService;

    @AutoModel
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
    public R save(@RequestBody BlogVersionEntity version) {
        return blogVersionService.saveOrUpdate(version) ? R.ok() : R.error("保存失败");
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = "application/json")
    public R list() {
        return R.ok(blogVersionService.list(new QueryWrapper<>()));
    }
}
