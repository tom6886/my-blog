package tb.blog.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.FileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tb.blog.server.data.dao.BlogDocDao;
import tb.blog.server.data.entity.BlogDocEntity;
import tb.blog.server.service.IBlogDocService;
import tb.blog.server.service.IQiniuService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author TB
 */
@Service("blogDocService")
public class BlogDocServiceImpl extends ServiceImpl<BlogDocDao, BlogDocEntity> implements IBlogDocService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final IQiniuService qiNiuService;

    @Autowired
    public BlogDocServiceImpl(IQiniuService qiNiuService) {
        this.qiNiuService = qiNiuService;
    }

    @Override
    public void deleteDbExtra() {
        List<BlogDocEntity> docs = this.baseMapper.queryExtra();

        if (docs.size() == 0) {
            logger.error("未查询到无效图片");
            return;
        }

        List<String> docKeys = this.baseMapper.queryNoRepeatExtra();

        logger.error(String.format("查询到有 %s 张无效图片", docKeys.size()));

        if (docKeys.size() == 0) {
            logger.error("七牛云删除文件失败，本次清理操作取消");
            return;
        }

        List<String> deleted = deleteQiNiuDoc(docKeys);
        logger.error(String.format("成功从七牛云删除 %s 张图片", deleted.size()));

        List<Long> ids = docs.stream().map(BlogDocEntity::getId).collect(Collectors.toList());
        int removed = this.baseMapper.deleteBatchIds(ids);
        logger.error(String.format("成功从数据库删除 %s 条记录", removed));
    }

    @Override
    public void deleteQiNiuExtra() {
        try {
            List<FileInfo> list = qiNiuService.fileList();

            if (list.size() == 0) {
                logger.error("未查询到七牛云上图片信息");
                return;
            }

            List<String> qiNiuKeys = new ArrayList<>();

            for (FileInfo item : list) {
                qiNiuKeys.add(item.key);
            }

            List<String> dbKeys = this.baseMapper.queryAllKeys();

            List<String> extraKeys = qiNiuKeys.stream().filter(x -> !dbKeys.contains(x)).collect(Collectors.toList());

            if (extraKeys.size() == 0) {
                logger.error("没有查询到需要删除的文件");
                return;
            }

            logger.error(String.format("查询到 %s 张需要删除的图片", extraKeys.size()));
            List<String> deleted = deleteQiNiuDoc(extraKeys);
            logger.error(String.format("成功从七牛云删除 %s 张图片", deleted.size()));
        } catch (QiniuException e) {
            logger.error("调用七牛云查询文件接口失败");
            e.printStackTrace();
        }
    }

    @Override
    public int deleteByArticleId(String pageId) {
        List<String> extras = this.baseMapper.queryNoRepeatExtraByArticleId(pageId);
        if (extras.size() > 0) {
            deleteQiNiuDoc(extras);
        }
        return this.baseMapper.delete(new QueryWrapper<BlogDocEntity>().eq("article_id", pageId));
    }

    /**
     * 调用七牛云接口 批量删除文件
     *
     * @param docKeys 需要删除的文件key
     * @return 删除成功的文件list
     */
    private List<String> deleteQiNiuDoc(List<String> docKeys) {
        try {
            String[] keys = docKeys.toArray(new String[0]);

            Response response = qiNiuService.deleteBatchFile(keys);
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);

            ArrayList<String> success = new ArrayList<>();

            for (int i = 0; i < keys.length; i++) {
                BatchStatus status = batchStatusList[i];
                String key = keys[i];
                if (status.code == 200) {
                    success.add(key);
                }
            }

            return success;
        } catch (QiniuException e) {
            logger.error("七牛云删除文件失败，发生错误：" + e.getMessage());
            return new ArrayList<>();
        }
    }
}
