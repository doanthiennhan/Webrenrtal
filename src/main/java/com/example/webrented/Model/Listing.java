package com.example.webrented.Model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Data
@EntityScan
@Document(collection = "listings")
public class Listing {
    @Id
    private String id;
    private String accountId;
    private String title;
    private String description;
    private String address;
    private String district;
    private double price;
    private double area;
    private List<String> images;
    private String available;
    private Date createdAt;
    private Date updatedAt;

    // Constructors

    // Getters and Setters
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String isAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
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