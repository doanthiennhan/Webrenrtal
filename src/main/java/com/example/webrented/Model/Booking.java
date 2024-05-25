package com.example.webrented.Model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@EntityScan
@Data
@Document(collection = "Booking")
public class Booking {
    @Id
    private String id;
    private String listingId;
    private String userId;
    private Date startDate;
    private Date endDate;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    // Constructors

    // Getters and Setters
    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime localDateTime) {
        this.startDate = java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime localDateTime) {
        this.endDate = java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime localDateTime) {
        this.createdAt = java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime localDateTime) {
        this.updatedAt = java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
