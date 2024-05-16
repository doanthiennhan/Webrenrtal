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
import org.springframework.web.bind.annotation.RequestBody;

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
    public String postDetail(@PathVariable("id") String id, Model model) {

        Listing listings = listingService.findById(id);
        System.out.println(listings.toString());
        if (listings != null) {
            String accountId = listings.getAccountId();
            System.out.println(accountId);

            List<Comment> comments = commentRepository.findByListingId(listings.getId());

            HashMap<Comment, String> imap = new HashMap<Comment, String>();
            for (Comment comment : comments) {
                System.out.println(comment.getId());
                imap.put(comment, accountService.findById(comment.getUserId()).getName());
                System.out.println(accountService.findById(comment.getUserId()).getName());
            }
            Account accounts = accountService.findById(accountId);

            if (accounts != null) {
                // Đưa dữ liệu vào model
                model.addAttribute("listings", listings);
                model.addAttribute("accounts", accounts);

                model.addAttribute("comments", imap);

                return "shop_product_detail";
            }
        }

        return "error";
    }

    @PostMapping("/postDetail/{id}")
    public String commentbaiviet(@PathVariable("id") String id, Model model, HttpSession session) {
        try {
            Account account = (Account) session.getAttribute("account");
            if (session.getAttribute("account") != null) {
                System.out.println(account.getId());
            } else {
                return "redirect:/login";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return id;
    }

}
