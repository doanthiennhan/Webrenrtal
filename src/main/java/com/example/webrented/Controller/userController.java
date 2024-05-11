package com.example.webrented.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class userController {
    @GetMapping("/userProfile")
    public String getMethodName() {
        return "userProfile";
    }

}
