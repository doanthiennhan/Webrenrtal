package com.example.webrented.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.webrented.Model.Account;
// import com.example.webrented.Model.Listing;
import com.example.webrented.repository.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    // Constructor injection to inject AccountRepository
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void xoaAcount(String id) {

        accountRepository.deleteById(id);
    }

    public void updateAccount(String id, String trangThai) {

        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();

            account.setStatus(trangThai);

            accountRepository.save(account);
        } else {

            throw new RuntimeException("Không tìm thấy tài khoản với ID: " + id);
        }
    }

    public Account findById(String accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

}
