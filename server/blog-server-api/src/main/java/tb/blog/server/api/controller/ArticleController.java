package tb.blog.server.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tb.blog.server.api.util.R;
import tb.blog.server.data.entity.BlogArticleEntity;
import tb.blog.server.service.IBlogArticleService;

import java.util.Date;

/**
 * @author TB
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IBlogArticleService blogArticleService;

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
    public R save(@RequestBody BlogArticleEntity article) {
        Long userName = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        article.setCreateBy(userName);
        article.setCreateTime(new Date());
        return blogArticleService.saveOrUpdate(article) ? R.ok() : R.error("保存失败");
    }
}
