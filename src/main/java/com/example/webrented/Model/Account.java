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
@Document(collection = "Accounts")
public class Account {
    @Id
    private String id;
    private String phone;
    private String password;

    private String role;
    private Date createdAt;
    private Date updatedAt;

    // Constructors
    public Account() {
    }

    public Account(String phone, String password, String role, Date createdAt, Date updatedAt) {
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    // public void setCreatedAt(LocalDateTime localDateTime) {
    // this.createdAt = localDateTime;
    // }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    // public void setUpdatedAt(LocalDateTime localDateTime) {
    // this.updatedAt = localDateTime;
    // }

    public void setCreatedAt(LocalDateTime localDateTime) {
        this.createdAt = java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public void setUpdatedAt(LocalDateTime localDateTime) {
        this.updatedAt = java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
