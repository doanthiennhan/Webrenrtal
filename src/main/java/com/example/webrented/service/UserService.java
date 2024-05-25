package com.example.webrented.service;

import org.springframework.stereotype.Service;

import com.example.webrented.Model.User;
import com.example.webrented.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByAccoutid(String AccountId) {
        User user = userRepository.findByAccountId(AccountId);
        return user;

    }

    public void updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            // Cập nhật thông tin của người dùng từ đối tượng user được truyền vào
            existingUser.setFullName(user.getFullName());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            existingUser.setAddress(user.getAddress());
            existingUser.setEmail(user.getEmail());
            existingUser.setCccd(user.getCccd());
            java.util.Date a = user.getBirtday();
            existingUser.setBirtday1(a);
            existingUser.setGiotinh(user.getGiotinh());
            existingUser.setGiothieu(user.getGiothieu());

            // Lưu lại thông tin đã cập nhật vào cơ sở dữ liệu
            userRepository.save(existingUser);
        } else {
            System.out.println("adsadsa");
        }

    }
}
