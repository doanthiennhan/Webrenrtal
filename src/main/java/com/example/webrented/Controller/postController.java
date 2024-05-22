package com.example.webrented.Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.webrented.Model.Account;
import com.example.webrented.Model.Comment;
import com.example.webrented.Model.Listing;
import com.example.webrented.repository.AccountRepository;
import com.example.webrented.repository.CommentRepository;
import com.example.webrented.repository.ListingRepository;
import com.example.webrented.service.AccountService;
import com.example.webrented.service.CommentService;
import com.example.webrented.service.ListingService;

import org.springframework.ui.Model;

// import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

// import com.example.webrented.repository.ListingRepository;

@Controller
public class postController {
    private CommentRepository commentRepository;
    private CommentService commentService;
    // private final ListingRepository listingRepository;
    private final ListingService listingService;
    // private final AccountRepository accountRepository;
    private final AccountService accountService;
    private static final String UPLOAD_DIR = "src/main/resources/static/image/";

    public postController(ListingRepository listingRepository, ListingService listingService,

            AccountRepository accountRepository, AccountService accountService, CommentRepository commentRepository,
            CommentService commentService) {

        // this.listingRepository = listingRepository;
        this.listingService = listingService;
        this.accountService = accountService;
        this.commentRepository = commentRepository;
        this.commentService = commentService;
        // this.accountRepository = accountRepository;
    }

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

    @PostMapping("/post")
    public String post(@RequestParam("id") String id,
            @RequestParam("numberhouse") String numberhouse, @RequestParam("size") String size,
            @RequestParam("price") String price, @RequestParam("subject") String subject,
            @RequestParam("body") String body, @RequestParam("district") String district,
            @RequestParam("images") MultipartFile[] images

    ) {

        System.out.println(id + numberhouse + size + price + subject + body + district +
                "");
        Listing newlisting = new Listing();
        List<String> anh = new ArrayList<>();

        try {
            newlisting.setAccountId(id);
            newlisting.setAddress(numberhouse + ", " + district + ", Đà nẵng");
            double s = Double.parseDouble(size);
            double gia = Double.parseDouble(price);
            newlisting.setArea(s);
            newlisting.setPrice(gia);
            newlisting.setCreatedAt(LocalDateTime.now());
            newlisting.setUpdatedAt(LocalDateTime.now());
            newlisting.setAvailable("null");
            newlisting.setTitle(subject);
            newlisting.setDistrict(district);
            newlisting.setDescription(body);

            for (MultipartFile image : images) {
                String randomFileName = UUID.randomUUID().toString() + getFileExtension(image.getOriginalFilename());
                byte[] bytes = image.getBytes();
                Path path = Paths.get(UPLOAD_DIR + randomFileName);
                Files.write(path, bytes);
                anh.add(randomFileName);

            }
            newlisting.setImages(anh);
            listingService.addListing(newlisting);
            return "post";
        } catch (Exception e) {
            e.printStackTrace();
            return "Đăng tin thất bại!";
        }

    }

    private String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex > 0) {
            return fileName.substring(lastIndex);
        }
        return "";
    }

    @GetMapping("/postDetail")
    public String postDetail() {
        return "shop_product_detail";
    }

    @GetMapping("/postDetail/{id}")
    public String postDetail(@PathVariable("id") String id, Model model, HttpSession session) {
        Account account1 = (Account) session.getAttribute("account");
        Listing listings = listingService.findById(id);
        System.out.println(listings.toString());
        if (listings != null) {
            String accountId = listings.getAccountId();
            System.out.println(accountId);

            List<Comment> comments = commentRepository.findByListingId(listings.getId());

            HashMap<Comment, Account> imap = new HashMap<Comment, Account>();
            for (Comment comment : comments) {
                System.out.println(comment.getId());
                imap.put(comment, accountService.findById(comment.getUserId()));
                System.out.println(accountService.findById(comment.getUserId()).getName());
            }
            Account accounts = accountService.findById(accountId);
            boolean listOfUser = false;
            if (accounts != null) {
                // Đưa dữ liệu vào model
                if (account1 != null) {
                    if (account1.getId().equals(accounts.getId())) {
                        listOfUser = true;
                    }
                }
                model.addAttribute("listings", listings);
                model.addAttribute("accounts", accounts);

                model.addAttribute("comments", imap);
                model.addAttribute("listOfUser", listOfUser);

                return "shop_product_detail";
            }
        }

        return "error";
    }

    @PostMapping("/postDetail/{id}")
    public String commentbaiviet(@PathVariable("id") String id, Model model, HttpSession session,
            @RequestParam("noidung") String noidung) {
        try {
            Account account = (Account) session.getAttribute("account");
            if (account != null) {
                System.out.println("tam ha");
                System.out.println(account.getId());
                System.out.println(noidung);
                Comment comment = new Comment();
                comment.setListingId(id);
                comment.setContent(noidung);
                comment.setUserId(account.getId());
                comment.setCreatedAt(LocalDateTime.now());
                comment.setUpdatedAt(LocalDateTime.now());
                commentService.addComment(comment);
                // Add logic for handling the comment if needed
                return "redirect:/postDetail/" + id; // Correctly use the id variable
            } else {
                return "redirect:/login";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/error"; // Redirect to a specific error page or handle the error appropriately
        }
    }

    @GetMapping("/postEdit1/{id}")
    public String post1(@PathVariable("id") String id, Model model, HttpSession session) {
        Listing listing = listingService.findById(id);
        model.addAttribute("listing", listing);
        System.out.println("----------------------------------------------------");
        System.out.println(listing.getDistrict());
        System.out.println("----------------------------------------------------");
        return "postEdit"; // Chuyển hướng đến /postEdit/{id}
    }

    @PostMapping("/postEdit1/{id}")
    public String postEdit(@RequestParam("id") String id, @PathVariable("id") String id1,
            @RequestParam("numberhouse") String numberhouse, @RequestParam("size") String size,
            @RequestParam("price") String price, @RequestParam("subject") String subject,
            @RequestParam("body") String body, @RequestParam("district") String district,
            @RequestParam("images") MultipartFile[] images

    ) {

        System.out.println(id + numberhouse + size + price + subject + body + district +
                "");
        Listing newlisting = listingService.findById(id1);
        List<String> anh = new ArrayList<>();

        try {
            newlisting.setAccountId(id);
            newlisting.setAddress(numberhouse + ", " + district + ", Đà nẵng");
            double s = Double.parseDouble(size);
            double gia = Double.parseDouble(price);
            newlisting.setArea(s);
            newlisting.setPrice(gia);
            newlisting.setCreatedAt(LocalDateTime.now());
            newlisting.setUpdatedAt(LocalDateTime.now());
            newlisting.setAvailable("null");
            newlisting.setTitle(subject);
            newlisting.setDistrict(district);
            newlisting.setDescription(body);

            for (MultipartFile image : images) {
                String randomFileName = UUID.randomUUID().toString() + getFileExtension(image.getOriginalFilename());
                byte[] bytes = image.getBytes();
                Path path = Paths.get(UPLOAD_DIR + randomFileName);
                Files.write(path, bytes);
                anh.add(randomFileName);

            }
            newlisting.setImages(anh);
            listingService.saveOrUpdateListing(newlisting);
            return "post";
        } catch (Exception e) {
            e.printStackTrace();
            return "cập nhật thất bại!";
        }

    }

}
