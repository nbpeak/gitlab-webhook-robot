package io.github.ealenxie.webhook.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.ealenxie.webhook.DingRobotHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by EalenXie on 2021/12/1 9:17
 */
@RestController
public class GitlabWebhookAPI {

    private static final String EVENT_HEADER = "X-Gitlab-Event";

    @Resource
    private DingRobotHandler dingRobotHandler;

    @PostMapping("/actuator/gitlab/webhook")
    public ResponseEntity<String> pipeline(@RequestBody JsonNode requestBody, @RequestHeader(EVENT_HEADER) String event) {
        return dingRobotHandler.handle(requestBody, event);
    }
}
