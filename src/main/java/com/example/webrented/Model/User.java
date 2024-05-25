package com.example.webrented.Model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@EntityScan
@Document(collection = "User")
public class User {
    @Id
    private String id;
    private String accountId;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String email;
    private String cccd;
    private java.util.Date birtday;
    private String giotinh;
    private String giothieu;

    public User() {
    }

    public User(String id, String accountId, String fullName, String phoneNumber, String address, String email,
            String cccd, Date birtday, String giotinh, String giothieu) {
        this.id = id;
        this.accountId = accountId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.cccd = cccd;
        this.birtday = birtday;
        this.giotinh = giotinh;
        this.giothieu = giothieu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public java.util.Date getBirtday() {
        return birtday;
    }

    public void setBirtday(LocalDateTime localDateTime) {
        this.birtday = java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public String getGiotinh() {
        return giotinh;
    }

    public void setGiotinh(String giotinh) {
        this.giotinh = giotinh;
    }

    public String getGiothieu() {
        return giothieu;
    }

    public void setGiothieu(String giothieu) {
        this.giothieu = giothieu;
    }
}