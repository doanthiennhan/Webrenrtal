package com.example.webrented.Controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;





@Controller
public class IndexController {
    @GetMapping("/")
    public String index () {
        return "index";
    }
    @GetMapping("/tam")
    public String tam () {
        return "tam";
    }
    
       
}
