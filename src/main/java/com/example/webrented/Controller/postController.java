package com.example.webrented.Controller;

import java.util.HashMap;
import java.util.List;

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

// import com.example.webrented.repository.ListingRepository;

@Controller
public class postController {
    private CommentRepository commentRepository;
    private CommentService commentService;
    // private final ListingRepository listingRepository;
    private final ListingService listingService;
    // private final AccountRepository accountRepository;
    private final AccountService accountService;

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
    public String post() {
        return "post";
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
}
