package com.java3y.austin.service.deduplication;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.java3y.austin.domain.TaskInfo;
import org.springframework.stereotype.Service;

/**
 * @author 3y
 * @date 2021/12/11
 * 内容去重服务（默认5分钟相同的文案发给相同的用户去重）
 */
@Service
public class ContentAbstractDeduplicationService extends AbstractDeduplicationService {

    /**
     * 内容去重 构建key
     * <p>
     * key: md5(templateId + templateId + content)
     * <p>
     * 相同的内容相同的模板短时间内发给同一个人
     *
     * @param taskInfo
     * @return
     */
    @Override
    public String deduplicationSingleKey(TaskInfo taskInfo, String receiver) {
        return DigestUtil.md5Hex(taskInfo.getMessageTemplateId() + receiver
                + JSON.toJSONString(taskInfo.getContentModel()));
    }
}
