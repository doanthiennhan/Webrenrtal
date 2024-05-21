package com.example.webrented.service;

import java.util.List;
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

    public int countUser(String a) {
        int dem = 0;
        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts) {
            if (account.getStatus().equals("cấm")) {
                dem++;
            }
        }
        return dem;
    }

    public Account findById(String accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

}
