package tb.blog.server.api.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.model.BatchStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tb.blog.server.api.util.R;
import tb.blog.server.config.util.UserContext;
import tb.blog.server.data.entity.BlogDocEntity;
import tb.blog.server.service.IBlogDocService;
import tb.blog.server.service.IQiniuService;

import java.io.IOException;
import java.util.Date;

/**
 * @author tangbo
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Value("${qiniu.prefix}")
    private String prefix;

    private final IQiniuService qiNiuService;

    private final IBlogDocService blogDocService;

    @Autowired
    public UploadController(IQiniuService qiNiuService, IBlogDocService blogDocService) {
        this.qiNiuService = qiNiuService;
        this.blogDocService = blogDocService;
    }

    @RequestMapping(value = "/yun", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R yun(@RequestParam("id") String id, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return R.error("文件内容为空");
        }

        try {
            Response response = qiNiuService.uploadFile(file.getInputStream());

            if (!response.isOK()) {
                return R.error("文件上传失败");
            }

            JSONObject jsonObject = JSONUtil.parseObj(response.bodyString());
            String yunFileName = jsonObject.getStr("key");
            String yunFilePath = StrUtil.appendIfMissing("http://" + prefix, "/") + yunFileName;

            // 将图片数据保存到数据库
            BlogDocEntity doc = new BlogDocEntity();
            doc.setArticleId(id);
            doc.setName(yunFileName);
            doc.setPath(yunFilePath);
            doc.setUploadTime(new Date());
            doc.setUploadBy(UserContext.get());

            boolean success = blogDocService.save(doc);

            return success ? R.ok(yunFilePath) : R.error("保存文件信息失败");
        } catch (IOException e) {
            return R.error("文件上传失败");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = "application/json")
    public R delete(@RequestBody String[] keys) {
        try {
            Response response = qiNiuService.deleteBatchFile(keys);
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
            return R.ok();
        } catch (QiniuException e) {
            return R.error("文件上传失败");
        }
    }
}
