package com.example.webrented.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.example.webrented.Model.Account;
import com.example.webrented.Model.Listing;
import com.example.webrented.Model.Renter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.webrented.repository.AccountRepository;
import com.example.webrented.repository.ListingRepository;
import com.example.webrented.repository.RenterRepository;

import jakarta.servlet.http.HttpSession;
// import org.springframework.web.bind.annotation.RequestBody;

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
    public String Login(Model model, HttpSession session) {
        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        if (session.getAttribute("account") != null) {
            // Nếu đã đăng nhập, chuyển hướng đến trang chủ
            return "redirect:/";
        }

        // Nếu chưa đăng nhập, hiển thị trang đăng nhập bình thường
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

    @GetMapping("/logout")
    public String postMethodName(HttpSession session, Model model) {

        session.removeAttribute("account");
        if (session.getAttribute("account") != null) {
            System.out.println("Oke ĐĂng xuất được rồi ");
        }
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String Register(Model model) {

        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("phone") String phone,
            @RequestParam("password") String password,
            @RequestParam("name") String name, Model model) {
        try {
            // Tạo một đối tượng Account mới
            Account newAccount = new Account();

            // Thiết lập các thuộc tính cho đối tượng Account mới
            newAccount.setPassword(password);
            newAccount.setPhone(phone);
            newAccount.setRole("renter");
            newAccount.setCreatedAt(LocalDateTime.now());
            newAccount.setUpdatedAt(LocalDateTime.now());

            // Lưu đối tượng Account mới vào cơ sở dữ liệu
            accountRepository.save(newAccount);
            String accountId = newAccount.getId();
            System.out.println("ID của account mới là " + newAccount.getId());

            // Tạo một đối tượng Renter mới
            Renter newRenter = new Renter();
            newRenter.setAccountId(accountId); // Sử dụng id của Account mới lưu
            newRenter.setFullName(name);
            newRenter.setPhoneNumber(phone);
            newRenter.setAddress("");

            // Lưu đối tượng Renter mới vào cơ sở dữ liệu
            renterRepository.save(newRenter);

            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập

        } catch (Exception e) {
            // Xử lý ngoại lệ: in ra thông báo lỗi và chuyển hướng về trang đăng ký
            e.printStackTrace();
            System.out.println("lỗi -------------------------------------------------------------------------------");
            model.addAttribute("errorMessage", "Đã xảy ra lỗi. Vui lòng thử lại sau.");
            return "register";
        }
    }

}
