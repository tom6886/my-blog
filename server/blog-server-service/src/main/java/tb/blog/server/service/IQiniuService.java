package tb.blog.server.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.InputStream;

/**
 * @author tangbo
 */
public interface IQiniuService {
    /**
     * 七牛云上传文件
     *
     * @param file 文件
     * @return 七牛上传Response
     * @throws QiniuException 七牛异常
     */
    Response uploadFile(InputStream file) throws QiniuException;

    /**
     * 七牛云批量删除文件
     *
     * @param keys 文件key
     * @return 七牛上传Response
     * @throws QiniuException 七牛异常
     */
    Response deleteBatchFile(String[] keys) throws QiniuException;
}
