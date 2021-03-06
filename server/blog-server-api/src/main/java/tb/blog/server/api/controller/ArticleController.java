package tb.blog.server.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tb.blog.server.api.util.R;
import tb.blog.server.config.annotation.AutoModel;
import tb.blog.server.data.entity.BlogArticleEntity;
import tb.blog.server.service.IBlogArticleService;

import java.util.Map;

/**
 * @author TB
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    private final IBlogArticleService blogArticleService;

    @Autowired
    public ArticleController(IBlogArticleService blogArticleService) {
        this.blogArticleService = blogArticleService;
    }

    @AutoModel
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
    public R save(@RequestBody BlogArticleEntity article) {
        return blogArticleService.saveOrUpdate(article) ? R.ok() : R.error("保存失败");
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = "application/json")
    public R list(@RequestBody Map<String, Object> params) {
        return R.ok(blogArticleService.queryPage(params));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, consumes = "application/json")
    public R delete(@PathVariable Long id) {
        return blogArticleService.deleteById(id) > 0 ? R.ok() : R.error("删除失败");
    }

    @RequestMapping(value = "/entity/{id}", method = RequestMethod.POST, consumes = "application/json")
    public R query(@PathVariable Long id) {
        return R.ok(blogArticleService.getById(id));
    }
}
