package com.example.webrented.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.example.webrented.Model.Account;
import com.example.webrented.Model.Listing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.webrented.repository.AccountRepository;
import com.example.webrented.repository.ListingRepository;
import com.example.webrented.repository.RenterRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {
    private final ListingRepository listingRepository;
    private final AccountRepository accountRepository;
    private final RenterRepository renterRepository;

    public IndexController(ListingRepository listingRepository, AccountRepository accountRepository,
            RenterRepository renterRepository) {
        this.listingRepository = listingRepository;
        this.accountRepository = accountRepository;
        this.renterRepository = renterRepository;

    }

    @GetMapping("/")
    public String index(Model model) {
        List<Listing> listings = listingRepository.findAll();
        model.addAttribute("listings", listings);

        return "index";
    }

    @GetMapping("/login")
    public String Login(Model model) {

        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("phone") String phone, @RequestParam("password") String password, Model model,
            HttpSession session) {
        // Kiểm tra xem có bản ghi nào trong cơ sở dữ liệu khớp với số điện thoại và mật
        // khẩu được cung cấp không
        Account account = accountRepository.findByPhoneAndPassword(phone, password);

        if (account != null) {
            // Nếu có bản ghi khớp, đăng nhập thành công
            // Lưu thông tin Account vào session
            session.setAttribute("account", account);

            return "redirect:/"; // Chuyển hướng đến trang chủ
        } else {
            // Xử lý khi đăng nhập không thành công
            model.addAttribute("error", "Số điện thoại hoặc mật khẩu không chính xác");
            return "login";
        }
    }

    @GetMapping("/register")
    public String Register(Model model) {
        return "register";
    }

    // @GetMapping("/admin")
    // public String admin(Model model) {

    //     return "admin_trangchu";
    // }

    // @GetMapping("/admin_quanlibaiviet")
    // public String admin_quanlibaiviet(Model model) {

    //     return "admin_quanlibaiviet";
    // }

    // @GetMapping("/admin_quanlibaiviet_daxoa")
    // public String admin_quanlibaiviet_daxoa(Model model) {

    //     return "admin_quanlibaiviet_daxoa";
    // }

    // @GetMapping("/admin_quanlibaiviet_daduyet")
    // public String admin_quanlibaiviet_daduyet(Model model) {

    //     return "admin_quanlibaiviet_daduyet";
    // }

}
