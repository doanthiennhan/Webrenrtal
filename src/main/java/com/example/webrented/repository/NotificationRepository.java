package com.example.webrented.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.webrented.Model.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findAll();

    List<Notification> findByUserId(String userId);

}
