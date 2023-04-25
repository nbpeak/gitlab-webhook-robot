package io.github.ealenxie.gitlab.webhook.sender;

import io.github.ealenxie.gitlab.GitlabHandler;
import io.github.ealenxie.gitlab.webhook.conf.WebHookConfig;
import io.github.ealenxie.gitlab.webhook.dto.MarkDownMsg;
import io.github.ealenxie.wechat.WeChatClient;
import io.github.ealenxie.wechat.dto.Markdown;
import io.github.ealenxie.wechat.message.MarkdownMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by EalenXie on 2022/2/11 16:42
 */
@Component
@ConditionalOnProperty(prefix = WebHookConfig.PREFIX, value = "way", havingValue = "wechat")
public class WeChatMessageSender implements MessageSender<MarkDownMsg, String> {
    private final WeChatClient weChatClient;
    private final GitlabHandler gitlabHandler;
    private final WebHookConfig webHookConfig;

    public WeChatMessageSender(WebHookConfig webHookConfig, RestTemplate httpClientRestTemplate, @Autowired(required = false) GitlabHandler gitlabHandler) {
        this.weChatClient = new WeChatClient(httpClientRestTemplate);
        this.gitlabHandler = gitlabHandler;
        this.webHookConfig = webHookConfig;
    }


    @Override
    public ResponseEntity<String> sendMessage(MarkDownMsg markDownMsg) {
        Markdown markdown = new Markdown();
        StringBuilder sb = new StringBuilder();
        if (!markDownMsg.notifier().isEmpty()) {
            List<String> atMobiles = new ArrayList<>();
//            if (gitlabHandler != null) {
//                List<String> notifier = markDownMsg.notifier();
//                for (String s : notifier) {
//                    String skype = gitlabHandler.getUserSkype(Long.parseLong(s));
//                    if (skype != null) {
//                        atMobiles.add(skype);
//                    }
//                }
//            }
            atMobiles.addAll(markDownMsg.notifier());
            markdown.setMentionedMobileList(atMobiles.toArray(new String[0]));
        }
        sb.append(markDownMsg.getMarkdown());
        markdown.setContent(sb.toString());
        MarkdownMessage actionCardMessage = new MarkdownMessage(markdown);
        return weChatClient.sendMessage(actionCardMessage, getKey(markDownMsg.getMessageType()));
    }

    private String getKey(MessageTypeEnum messageType) {
        Supplier<String> keyFunc = () -> {
            if (messageType == null) {
                return null;
            }

            switch (messageType) {
                case COMMENT:
                    return webHookConfig.getWechat().getCommentKey();
                case MERGE_REQUEST_STATUS_CHANGED:
                    return webHookConfig.getWechat().getMergeRequestStatusChangedKey();
                default:
                    return webHookConfig.getWechat().getKey();
            }
        };

        return Optional.ofNullable(keyFunc.get())
                .orElse(webHookConfig.getWechat().getKey());
    }

}
