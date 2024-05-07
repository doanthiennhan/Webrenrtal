package com.example.webrented;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.example.webrented.Model.Account;

@Component
public class MongoDBConnectionChecker implements CommandLineRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {
        try {
            // Kiểm tra kết nối bằng cách thực hiện một thao tác đơn giản, ví dụ: lấy tên
            // tất cả các collection
            // mongoTemplate.getCollectionNames().forEach(System.out::println);
            // System.out.println("Kết nối MongoDB thành công!");
            mongoTemplate.getCollectionNames().forEach(System.out::println);
            System.out.println("Kết nối MongoDB thành công!");

            // Lấy danh sách tài khoản và in ra thông tin của từng tài khoản
            List<Account> accounts = mongoTemplate.findAll(Account.class);
            System.out.println("Danh sách tài khoản:");
            for (Account account : accounts) {
                System.out.println(account.toString());
            }
        } catch (Exception ex) {
            System.out.println("Lỗi khi kết nối MongoDB: " + ex.getMessage());
        }
    }
}
