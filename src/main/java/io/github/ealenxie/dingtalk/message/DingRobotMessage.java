package io.github.ealenxie.dingtalk.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.dingtalk.dto.DingTalkAt;

/**
 * Created by EalenXie on 2021/12/27 9:23
 */
public abstract class DingRobotMessage {

    @JsonProperty("msgtype")
    private String msgType;

    private DingTalkAt at;

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public DingTalkAt getAt() {
        return at;
    }

    public void setAt(DingTalkAt at) {
        this.at = at;
    }
}
