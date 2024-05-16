package com.example.webrented.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.webrented.Model.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByListingId(String listingId);

}
