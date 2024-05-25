package com.example.webrented.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.webrented.Model.Notification;

import com.example.webrented.repository.NotificationRepository;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Optional<Notification> getListingById(String id) {
        return notificationRepository.findById(id);
    }

    // Phương thức để lưu hoặc cập nhật một Listing
    public Notification saveOrUpdateListing(Notification notification) {
        return notificationRepository.save(notification);
    }

    public List<Notification> findListUserId(String id) {
        return notificationRepository.findByUserId(id);
    }

    // Phương thức để xóa một Listing dựa trên id
    public void deleteListingById(String id) {
        notificationRepository.deleteById(id);
    }

}
