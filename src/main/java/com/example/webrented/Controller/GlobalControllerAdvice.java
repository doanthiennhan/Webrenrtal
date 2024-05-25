package com.example.webrented.Controller;

import com.example.webrented.Model.Account;
import com.example.webrented.Model.Notification;
import com.example.webrented.service.NotificationService;

import jakarta.servlet.http.HttpSession;

import com.example.webrented.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void addNotificationsToModel(Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        String idAccouts = "";
        if (account != null) {
            idAccouts = account.getId();
        }
        if (!idAccouts.isEmpty()) {
            com.example.webrented.Model.User user = userRepository.findByAccountId(idAccouts);
            List<Notification> notifications = notificationService
                    .findListUserId(idAccouts);
            model.addAttribute("renter", user);
            model.addAttribute("thongbao", notifications);
        }
    }
}