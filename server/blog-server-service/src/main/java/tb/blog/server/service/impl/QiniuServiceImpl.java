package tb.blog.server.service.impl;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tb.blog.server.service.IQiniuService;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author tangbo
 */
@Service
public class QiniuServiceImpl implements IQiniuService, InitializingBean {
    private final UploadManager uploadManager;

    private final BucketManager bucketManager;

    private final Auth auth;

    @Value("${qiniu.bucket}")
    private String bucket;

    private StringMap putPolicy;

    @Autowired
    public QiniuServiceImpl(UploadManager uploadManager, BucketManager bucketManager, Auth auth) {
        this.uploadManager = uploadManager;
        this.bucketManager = bucketManager;
        this.auth = auth;
    }

    @Override
    public Response uploadFile(InputStream file) throws QiniuException {
        Response response = this.uploadManager.put(file, null, getUploadToken(), null, null);
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(file, null, getUploadToken(), null, null);
            retry++;
        }
        return response;
    }

    @Override
    public Response deleteBatchFile(String[] keys) throws QiniuException {
        BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
        batchOperations.addDeleteOp(bucket, keys);
        return bucketManager.batch(batchOperations);
    }

    @Override
    public List<FileInfo> fileList() throws QiniuException {
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, "");
        List<FileInfo> list = new ArrayList<>();

        while (fileListIterator.hasNext()) {
            FileInfo[] items = fileListIterator.next();
            list.addAll(Stream.of(items).collect(Collectors.toList()));
        }

        return list;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":$(imageInfo.width), \"height\":${imageInfo.height}}");
    }

    /**
     * 获取上传凭证
     *
     * @return 上传凭证
     */
    private String getUploadToken() {
        return this.auth.uploadToken(bucket, null, 3600, putPolicy);
    }
}
