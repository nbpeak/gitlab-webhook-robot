package io.github.ealenxie.webhook.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by EalenXie on 2021/12/1 9:40
 */
public class CommitVO implements Comparable<CommitVO> {

    private String id;

    private String message;

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
    private Date timestamp;

    private String url;

    private AuthorVO author;

    private String[] added;

    private String[] modified;

    private String[] removed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AuthorVO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorVO author) {
        this.author = author;
    }

    public String[] getAdded() {
        return added;
    }

    public void setAdded(String[] added) {
        this.added = added;
    }

    public String[] getModified() {
        return modified;
    }

    public void setModified(String[] modified) {
        this.modified = modified;
    }

    public String[] getRemoved() {
        return removed;
    }

    public void setRemoved(String[] removed) {
        this.removed = removed;
    }

    @Override
    public int compareTo(CommitVO o) {
        if (timestamp.after(o.timestamp)) {
            return -1;
        }
        if (timestamp.before(o.timestamp)) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
