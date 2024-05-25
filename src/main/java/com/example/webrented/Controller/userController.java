package com.example.webrented.Controller;

import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;

import com.example.webrented.Model.Account;
import com.example.webrented.Model.Listing;
import com.example.webrented.Model.User;
import com.example.webrented.service.AccountService;
import com.example.webrented.service.ListingService;
import com.example.webrented.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class userController {
    private ListingService listingService;
    private AccountService accountService;
    private UserService userService;

    public userController(ListingService listingService, AccountService accountService, UserService userService) {
        this.listingService = listingService;
        this.userService = userService;
        this.accountService = accountService;
    }

    @GetMapping("/userProfile")
    public String getMethodName() {
        return "userProfile";
    }

    @GetMapping("/account")
    public String getAcount() {
        return "account";
    }

    @GetMapping("/chitiettaikhoan")
    public String getAcount1String() {
        return "chitiettaikhoan";
    }

    @GetMapping("/userProfile/{id}")
    public String getMethodName1(@PathVariable("id") String id, Model model) {
        List<Listing> lists = listingService.findAllbaivietdaduyetByAccountId(id);
        Account tam = accountService.findById(id);
        String name = tam.getName();
        model.addAttribute("lists", lists);
        model.addAttribute("name", name);
        return "userProfile";
    }

    @GetMapping("/chitiettaikhoan/{id}")
    public String postBookRoom(@PathVariable("id") String id, Model model) {
        Account tam = accountService.findById(id);
        model.addAttribute("Account", tam);
        String idaccout = tam.getId();
        User user = userService.findByAccoutid(idaccout);
        model.addAttribute("User", user);
        return "chitiettaikhoang";
    }

    @PostMapping("/chitiettaikhoan/{id}")
    public String updateUserDetails(@PathVariable("id") String id,
            @RequestParam("email") String email,
            @RequestParam("address") String address,
            @RequestParam("gender") String gender,
            @RequestParam("dob") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob,
            @RequestParam("bio") String bio,
            @RequestParam("fullName") String fullName,
            @RequestParam("phoneNumber") String phoneNumber,
            RedirectAttributes redirectAttributes) {

        // Kiểm tra xem tài khoản có tồn tại không
        Account account = accountService.findById(id);
        if (account == null) {
            // Xử lý trường hợp tài khoản không tồn tại
            return "error"; // hoặc redirect tới một trang lỗi khác
        }

        // Lấy thông tin người dùng
        User user = userService.findByAccoutid(account.getId());
        if (user == null) {
            // Xử lý trường hợp người dùng không tồn tại
            return "error"; // hoặc redirect tới một trang lỗi khác
        }

        // Cập nhật thông tin người dùng
        user.setEmail(email);
        user.setAddress(address);
        user.setGiotinh(gender);
        user.setGiothieu(bio);
        user.setBirtday1(dob);
        user.setFullName(fullName);
        user.setPhoneNumber(phoneNumber);

        // Cập nhật thông tin người dùng trong cơ sở dữ liệu
        userService.updateUser(user);

        // Chuyển hướng đến trang chi tiết tài khoản
        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("Message", "Đăng nhập thành công");
        return "redirect:/chitiettaikhoan/" + id;
    }

}
