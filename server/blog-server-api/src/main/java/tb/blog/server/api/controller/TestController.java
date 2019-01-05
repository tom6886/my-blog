package tb.blog.server.api.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tb.blog.server.api.util.R;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/{id}")
    public R test1(@PathVariable String id) {
        return R.ok(id);
    }
}
