package com.example.webrented.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.webrented.Model.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {

}
