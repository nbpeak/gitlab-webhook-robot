package io.github.ealenxie.gitlab.webhook.dto.note;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.gitlab.webhook.dto.*;
import io.github.ealenxie.gitlab.webhook.dto.mergerequest.MergeRequestHook;
import io.github.ealenxie.gitlab.webhook.sender.MessageTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

/**
 * Created by EalenXie on 2022/1/21 15:51
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown=true)
public class NoteHook implements MarkDownMsg {

    @JsonProperty("object_kind")
    private String objectKind;
    private User user;
    @JsonProperty("project_id")
    private Long projectId;
    private Project project;
    @JsonProperty("object_attributes")
    private ObjectAttributes objectAttributes;
    private Repository repository;
//    @JsonProperty("event_type")
//    private String eventType;
//    private Issue issue;
    @JsonProperty("merge_request")
    private MergeRequestHook.ObjectAttributes mergeRequest;

    @Override
    public String getTitle() {
        return getObjectKind();
    }

    @Override
    public List<String> notifier() {
//        return Collections.singletonList(String.valueOf(user.getId()));
        return Collections.singletonList(String.valueOf(user.getName()));
    }

    @Override
    public MessageTypeEnum getMessageType() {
        return MessageTypeEnum.COMMENT;
    }

    @Override
    public String getMarkdown() {
        StringBuilder sb = new StringBuilder(new Emoji("\uD83D\uDCDD ").toString());
        String u = String.format("[%s](%s)", user.getName(), UserUtils.getUserHomePage(project.getWebUrl(), user.getUsername()));

        String p = String.format("[[%s]](%s)", project.getName(), project.getWebUrl());
        if (mergeRequest != null) {
            String sources = String.format("[%s](%s/-/tree/%s)", mergeRequest.getSourceBranch(), project.getWebUrl(), mergeRequest.getSourceBranch());
            String targets = String.format("[%s](%s/-/tree/%s)", mergeRequest.getTargetBranch(), project.getWebUrl(), mergeRequest.getTargetBranch());
            String merge = String.format(" [#%s](%s)(%s)", mergeRequest.getId(), mergeRequest.getUrl(), mergeRequest.getTitle());
            sb.append(String.format("<font color='#000000'>%s 在 %s %s ➔➔ %s %s 发布了一条评论，[查看详情](%s) </font>%n%n", u, p, sources, targets, merge, objectAttributes.getUrl()));
        } else {
            sb.append(String.format("<font color='#000000'>%s 在 %s 发布了一条评论，[查看详情](%s) </font>%n", u, p, objectAttributes.getUrl()));
        }
        sb.append(String.format("%s%n%n", objectAttributes.getUrl()));
//        String i = String.format("[#%s](%s)", issue.getId(), issue.getUrl());
//        String n = String.format("[%s](%s)", objectKind, objectAttributes.getUrl());
//        sb.append(String.format("<font color='#000000'>%s%s add new %s in Issue[%s]</font>%n%n", u, new Emoji("\uD83E\uDDD0"), n, i));
//        sb.append(String.format("**%s**%n%n>%s%n", issue.getTitle(), objectAttributes.getNote()));
        sb.append(String.format(objectAttributes.getNote()));
        return sb.toString();
    }

    @Setter
    @Getter
    public static class ObjectAttributes {
        private Long id;
        private String note;
        @JsonProperty("noteable_type")
        private String noteAbleType;
        @JsonProperty("author_id")
        private Long authorId;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("updated_at")
        private String updatedAt;
        private Long projectId;
        private String attachment;
        @JsonProperty("line_code")
        private String lineCode;
        @JsonProperty("commit_id")
        private String commitId;
        @JsonProperty("noteable_id")
        private Long noteAbleId;
        private Boolean system;
        @JsonProperty("st_diff")
        private String stDiff;
        private String url;
//        @JsonProperty("change_position")
//        private String changePosition;
//        @JsonProperty("discussion_id")
//        private String discussionId;
//        @JsonProperty("original_position")
//        private String originalPosition;
//        private String position;
//        @JsonProperty("resolved_at")
//        private String resolvedAt;
//        @JsonProperty("resolved_by_id")
//        private String resolvedById;
//        @JsonProperty("resolved_by_push")
//        private String resolvedByPush;
//        private String type;
//        @JsonProperty("updated_by_id")
//        private Long updatedById;
//        private String description;
    }
}
