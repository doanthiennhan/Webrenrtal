package com.example.webrented.Model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@EntityScan
@Document(collection = "Message")
public class Notification {
    @Id
    private String id;
    private String userId;
    private String listingId;
    private String content;
    private Date createdAt;
    private Date updatedAt;

    public Notification() {
    }

    public Notification(String userId, String listingId, String content, Date createdAt, Date updatedAt) {
        this.userId = userId;
        this.listingId = listingId;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(LocalDateTime localDateTime) {
        this.createdAt = java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public void setUpdatedAt(LocalDateTime localDateTime) {
        this.updatedAt = java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
