package io.github.ealenxie.gitlab.webhook.sender;

/**
 * @author LiuFeng
 * @since 2023/04/13 20:09
 */
public enum MessageTypeEnum {
    /**
     * 默认消息
     */
    DEFAULT,
    /**
     * 评论消息
     */
    COMMENT,
    /**
     * Merge Request 请求状态变更消息
     */
    MERGE_REQUEST_STATUS_CHANGED
}
