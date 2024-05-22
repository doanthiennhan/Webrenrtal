package com.example.webrented.Controller;

import java.util.ArrayList;
import java.util.HashMap;
// import java.util.HashMap;
import java.util.List;

import com.example.webrented.service.AccountService;
import com.example.webrented.service.ListingService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.webrented.Model.Listing;

import com.example.webrented.Model.Account;

import com.example.webrented.repository.AccountRepository;
import com.example.webrented.repository.ListingRepository;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class adminController {

    private final ListingRepository listingRepository;
    private final ListingService listingService;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    public adminController(ListingRepository listingRepository, ListingService listingService,
            AccountRepository accountRepository, AccountService accountService) {

        this.listingRepository = listingRepository;
        this.listingService = listingService;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/admin")
    public String admin(Model model, HttpSession session) {
        List<Double> list = new ArrayList<>();
        try {
            Account account = (Account) session.getAttribute("account");
            if (session.getAttribute("account") != null) {

                if (account.getRole().equals("admin") == false) {

                    return "redirect:/";
                }

            } else {

                return "redirect:/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ ở đây
            return "redirect:/";
        }
        double allUser = (double) accountRepository.findAll().size();

        double User = (double) accountService.countUser("bình thường");
        double cam = (User / allUser) * 100;
        list.add(100 - cam);
        list.add(cam);
        int allAccount = accountRepository.findAll().size();
        model.addAttribute("allAccount", allAccount);

        double alllist = (double) listingRepository.findAll().size();
        double acceptList = (double) listingService.availableCount("true");
        double canceltList = (double) listingService.availableCount("false");
        double newtList = (double) listingService.availableCount("null");

        list.add((acceptList / alllist) * 100);
        list.add((canceltList / alllist) * 100);
        list.add((100 - (list.get(2) - list.get(3))));
        System.out.println(alllist + " " + acceptList + " " + canceltList + " " + newtList + " "
                + ((canceltList / alllist) * 100) + " " +
                +(100 - (list.get(2) - list.get(3))));
        int all = listingRepository.findAll().size();
        model.addAttribute("allAccount", allAccount);

        model.addAttribute("list", list);
        model.addAttribute("all", all);

        return "admin_trangchu";

    }

    @GetMapping("/admin_quanlibaiviet")
    public String admin_quanlibaiviet(Model model, HttpSession session) {
        List<Listing> listings = listingRepository.findByAvailable("null");

        try {
            Account account = (Account) session.getAttribute("account");
            if (session.getAttribute("account") != null) {

                if (account.getRole().equals("admin") == false) {
                    return "redirect:/";
                }
                model.addAttribute("listings", listings);

            } else {

                return "redirect:/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ ở đây
            return "redirect:/";
        }
        return "admin_quanlibaiviet.html";
    }

    @GetMapping("/admin_quanlibaiviet_daxoa")
    public String admin_quanlibaiviet_daxoa(Model model, HttpSession session) {
        List<Listing> listings = listingRepository.findByAvailable("false");

        try {
            Account account = (Account) session.getAttribute("account");
            if (session.getAttribute("account") != null) {

                if (account.getRole().equals("admin") == false) {
                    return "redirect:/";
                }
                model.addAttribute("listings", listings);

            } else {

                return "redirect:/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ ở đây
            return "redirect:/";
        }
        return "admin_quanlibaiviet_daxoa.html";
    }

    @PostMapping("/admin_quanlibaiviet_daxoa")
    public String postMethodName(@RequestParam("id") String id, @RequestParam("action") String action) {
        if ("khoiphuc".equals(action)) {
            // Nếu hành động là "duyet", thực hiện duyệt
            listingService.updateListingAvailability(id, "true");
        } else if ("xoa".equals(action)) {
            // Nếu hành động là "xoa", thực hiện xóa
            listingService.deleteListingById(id);
        }

        return "redirect:/admin_quanlibaiviet_daxoa";
    }

    @GetMapping("/admin_quanlibaiviet_daduyet")
    public String admin_quanlibaiviet_daduyet(Model model, HttpSession session) {
        List<Listing> listings = listingRepository.findByAvailable("true");
        try {
            Account account = (Account) session.getAttribute("account");
            if (session.getAttribute("account") != null) {

                if (account.getRole().equals("admin") == false) {
                    return "redirect:/";
                }
                model.addAttribute("listings", listings);

            } else {

                return "redirect:/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ ở đây
            return "redirect:/";
        }

        return "admin_quanlibaiviet_daduyet.html";
    }

    @PostMapping("/admin_quanlibaiviet")
    public String handleAction(@RequestParam("id") String id, @RequestParam("action") String action) {
        if ("duyet".equals(action)) {
            // Nếu hành động là "duyet", thực hiện duyệt
            listingService.updateListingAvailability(id, "true");
        } else if ("xoa".equals(action)) {
            // Nếu hành động là "xoa", thực hiện xóa
            listingService.updateListingAvailability(id, "false");
        }
        return "redirect:/admin_quanlibaiviet";
    }

    @PostMapping("/admin_quanlibaiviet_daduyet")
    public String updateListingAvailability(@RequestParam("id") String id) {
        // Đảm bảo phương thức này được gọi khi form được submit
        listingService.updateListingAvailability(id, "false");
        // Thực hiện các thao tác cần thiết
        return "redirect:/admin_quanlibaiviet_daduyet";
    }

    @GetMapping("/admin_quanlitaikhoan")
    public String admin_quanlitk(Model model, HttpSession session) {
        List<Account> accounts = accountRepository.findAll();
        HashMap<Account, Integer> idMap = new HashMap<Account, Integer>();
        for (Account account : accounts) {
            idMap.put(account, listingService.accountCount(account.getId()));
        }
        try {
            Account account = (Account) session.getAttribute("account");
            if (session.getAttribute("account") != null) {

                if (account.getRole().equals("admin") == false) {
                    return "redirect:/";
                }
                model.addAttribute("accounts", idMap);

            } else {

                return "redirect:/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ ở đây
            return "redirect:/";
        }

        return "qltk";
    }

    @PostMapping("/admin_quanlitaikhoan")
    public String quanlibaiviet(@RequestParam("id") String id, @RequestParam("action") String action) {
        if ("cam".equals(action)) {
            accountService.updateAccount(id, "cấm");
        } else if ("xoa".equals(action)) {
            accountService.xoaAcount(id);
        }
        return "redirect:/admin_quanlitaikhoan";
    }

}