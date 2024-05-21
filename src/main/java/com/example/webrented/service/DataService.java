package com.example.webrented.service;

import org.springframework.stereotype.Service;

import com.example.webrented.Controller.SseController;

@Service
public class DataService {

    private final SseController sseController;

    public DataService(SseController sseController) {
        this.sseController = sseController;
    }

    public void changeData(String newData) {
        // Cập nhật dữ liệu
        // ...

        // Thông báo tới các client qua SSE
        sseController.reload();
    }
}
