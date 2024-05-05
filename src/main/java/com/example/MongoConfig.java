package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    public MongoClient mongoClient() {
        try {
            return MongoClients.create();
        } catch (Exception e) {
            // Xử lý lỗi kết nối MongoDB ở đây
            // Ví dụ: in thông báo lỗi và ghi log
            System.err.println("Không thể kết nối đến MongoDB: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Không thể kết nối đến MongoDB", e);
        }
    }

    @Override
    protected String getDatabaseName() {
        return "webrented";
    }
}
