package io.github.ealenxie.gitlab.webhook.dto;

import io.github.ealenxie.gitlab.webhook.sender.MessageTypeEnum;

import java.util.Collections;
import java.util.List;

/**
 * Created by EalenXie on 2021/12/1 9:46
 * 生成markdown消息
 */
public interface MarkDownMsg {

    default MessageTypeEnum getMessageType() {
        return MessageTypeEnum.DEFAULT;
    }

    /**
     * 消息标题
     */
    default String getTitle() {
        return "undefined";
    }

    /**
     * 设置通知人
     */
    default List<String> notifier() {
        return Collections.emptyList();
    }

    /**
     * 生成markdown信息
     */
    String getMarkdown();
}
