package com.example.webrented.service;

import com.example.webrented.Model.Listing;
import com.example.webrented.repository.ListingRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ListingService {
    private final ListingRepository listingRepository;

    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    // Phương thức để lấy danh sách tất cả các Listing
    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    // Phương thức để lấy một Listing dựa trên id
    public Optional<Listing> getListingById(String id) {
        return listingRepository.findById(id);
    }

    // Phương thức để lưu hoặc cập nhật một Listing
    public Listing saveOrUpdateListing(Listing listing) {
        return listingRepository.save(listing);
    }

    // Phương thức để xóa một Listing dựa trên id
    public void deleteListingById(String id) {
        listingRepository.deleteById(id);
    }
    public void updateListingAvailability(String id, Boolean available) {
        System.out.println("b1");
        Optional<Listing> optionalListing = listingRepository.findById(id);
        System.out.println("b2");
        optionalListing.ifPresent(listing -> {
            listing.setAvailable(available);
            System.out.println("b3");
            listingRepository.save(listing);
        });
    }
}
