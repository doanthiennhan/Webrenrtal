package com.example.webrented.Controller;

import java.time.LocalDateTime;
import java.time.Period;

import org.springframework.stereotype.Controller;

import com.example.webrented.Model.Account;
import com.example.webrented.Model.Booking;
import com.example.webrented.Model.Listing;
import com.example.webrented.Model.Notification;
import com.example.webrented.service.BookingService;
import com.example.webrented.service.ListingService;
import com.example.webrented.service.NotificationService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class bookRoomController {
    private final BookingService bookingService;
    private final NotificationService notificationService;
    private final ListingService listingService;

    public bookRoomController(BookingService bookingService, NotificationService notificationService,
            ListingService listingService) {
        this.bookingService = bookingService;
        this.notificationService = notificationService;
        this.listingService = listingService;
    }

    @PostMapping("/notification/{id}")
    public String postBooking(@PathVariable("id") String id, Model model, HttpSession session,
            RedirectAttributes redirectAttributes) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return "redirect:/login";
        } else {

            Listing list = listingService.findById(id);
            String userid = list.getAccountId();
            listingService.updateListingAvailability(id, "dathue");
            System.out.println(
                    list.getAvailable() + " ########################################################################");
            Notification thongbao = new Notification();
            thongbao.setUserId(userid);
            thongbao.setContent(
                    "người dùng " + account.getName() + " muốn thuê trọ của bạn. Bấm vào để biết thêm chi tiết ");
            thongbao.setCreatedAt(LocalDateTime.now());
            thongbao.setUpdatedAt(LocalDateTime.now());
            notificationService.saveOrUpdateListing(thongbao);

            Booking booking = new Booking();
            booking.setListingId(id);
            booking.setStatus("dathue");
            booking.setUserId(userid);
            booking.setStartDate(LocalDateTime.now());
            booking.setEndDate(LocalDateTime.now().plus(Period.ofDays(30)));
            booking.setCreatedAt(LocalDateTime.now());
            booking.setUpdatedAt(LocalDateTime.now());
            bookingService.addBooking(booking);
            redirectAttributes.addFlashAttribute("Message", "Thuê Thành Công");

        }

        return "redirect:/postDetail/{id}";
    }

}
