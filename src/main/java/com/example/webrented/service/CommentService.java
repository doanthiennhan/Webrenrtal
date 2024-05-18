package com.example.webrented.service;

import org.springframework.stereotype.Service;

import com.example.webrented.Model.Comment;
// import com.example.webrented.repository.AccountRepository;
import com.example.webrented.repository.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    // private final AccountRepository accountRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;

    }

    public void addComment(Comment com) {
        commentRepository.save(com);

    }

}
