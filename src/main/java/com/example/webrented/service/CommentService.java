package com.example.webrented.service;

import java.util.Optional;

import org.attoparser.dom.Comment;
import org.springframework.stereotype.Service;

import com.example.webrented.Model.Account;
import com.example.webrented.repository.AccountRepository;
import com.example.webrented.repository.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;

    public CommentService(CommentRepository commentRepository, AccountRepository accountRepository) {
        this.commentRepository = commentRepository;
        this.accountRepository = accountRepository;
    }

    // public Comment findById(String commentId) {
    // return commentRepository.findallByLí(commentId).orElse(null);
    // }

}
