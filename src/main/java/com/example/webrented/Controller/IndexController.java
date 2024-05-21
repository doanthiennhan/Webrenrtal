package com.example.webrented.Controller;

import java.time.LocalDateTime;
import java.util.List;

// import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;
import org.springframework.stereotype.Controller;

import com.example.webrented.Model.Account;
import com.example.webrented.Model.Listing;
import com.example.webrented.Model.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.webrented.repository.AccountRepository;
import com.example.webrented.repository.ListingRepository;
import com.example.webrented.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {
    private final ListingRepository listingRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    String idAccouts = "";

    public IndexController(ListingRepository listingRepository, AccountRepository accountRepository,
            UserRepository userRepository) {
        this.listingRepository = listingRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;

    }

    @GetMapping("/")
    public String index(Model model) {
        try {
            List<Listing> listings = listingRepository.findByAvailable("true");
            model.addAttribute("listings", listings);
            User renter = userRepository.findByAccountId(idAccouts);
            if (idAccouts != "") {
                model.addAttribute("renter", renter);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ ở đây
        }
        return "index";
    }

    @GetMapping("/login")
    public String Login(Model model, HttpSession session) {
        try {
            if (session.getAttribute("account") != null) {
                return "redirect:/";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ ở đây
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("phone") String phone, @RequestParam("password") String password, Model model,
            HttpSession session) {

        try {
            Account account = accountRepository.findByPhoneAndPassword(phone, password);

            if (account != null) {
                if (account.getStatus().equals("cấm")) {
                    model.addAttribute("error", "Tài khoản của bạn đã bị cấm");
                    return "login";
                }

                session.setAttribute("account", account);
                idAccouts = account.getId();

                if (account.getRole().equals("admin")) {
                    return "redirect:/admin";
                } else {
                    return "redirect:/";
                }
            } else {
                model.addAttribute("error", "Số điện thoại hoặc mật khẩu không chính xác");
                return "login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ ở đây
            return "login";
        }
    }

    @GetMapping("/logout")
    public String postMethodName(HttpSession session, Model model) {
        try {
            session.removeAttribute("account");
            if (session.getAttribute("account") != null) {
                idAccouts = "";
                System.out.println("Oke ĐĂng xuất được rồi ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ ở đây
        }
        return "redirect:/login";
    }

    @GetMapping("/forget-password")
    public String getforgetpassword(Model model) {
        return "mk";
    }

    @GetMapping("/register")
    public String Register(Model model) {
        return "register";
    }

    @GetMapping("/test")
    public String getTest(Model model) {
        return "test";
    }

    @PostMapping("/register")
    public String register(@RequestParam("phone") String phone, @RequestParam("password") String password,
            @RequestParam("name") String name, Model model) {
        try {

            List<Account> account1 = accountRepository.findAll();
            for (Account account2 : account1) {
                if (account2.getPhone().equals(phone)) {
                    model.addAttribute("error", "Số điện thoại đã được đăng kí");
                    return "register";
                }
            }
            Account newAccount = new Account();
            newAccount.setPassword(password);
            newAccount.setPhone(phone);
            newAccount.setName(name);
            newAccount.setRole("renter");
            newAccount.setStatus("binh thường");
            newAccount.setCreatedAt(LocalDateTime.now());
            newAccount.setUpdatedAt(LocalDateTime.now());
            accountRepository.save(newAccount);
            String accountId = newAccount.getId();
            User newUser = new User();
            newUser.setAccountId(accountId);
            newUser.setFullName(name);
            newUser.setPhoneNumber(phone);
            newUser.setAddress("");
            userRepository.save(newUser);
            return "redirect:/login";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Đã xảy ra lỗi. Vui lòng thử lại sau.");
            return "register";
        }
    }
}
