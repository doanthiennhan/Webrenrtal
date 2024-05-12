package com.example.webrented.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

// import com.example.webrented.repository.ListingRepository;

@Controller
public class postController {
    // private ListingRepository listingRepository;

    @GetMapping("/post")
    public String post(Model model, HttpSession session) {
        try {
            if (session.getAttribute("account") != null) {
                return "post";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ ở đây
        }
        return "redirect:/login";
    }

    @GetMapping("/postDetail")
    public String postDetail() {
        return "postDetail";
    }
}
