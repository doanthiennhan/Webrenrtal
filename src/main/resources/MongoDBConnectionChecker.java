package com.example.webrented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoDBConnectionChecker implements CommandLineRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {
        try {
            // Kiểm tra kết nối bằng cách thực hiện một thao tác đơn giản, ví dụ: lấy tên
            // tất cả các collection
            mongoTemplate.getCollectionNames().forEach(System.out::println);
            System.out.println("Kết nối MongoDB thành công!");
        } catch (Exception ex) {
            System.out.println("Lỗi khi kết nối MongoDB: " + ex.getMessage());
        }
    }
}
