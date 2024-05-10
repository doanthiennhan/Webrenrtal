package com.example.webrented.repository;

// import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.webrented.Model.Account;

public interface AccountRepository extends MongoRepository<Account, String> {
    // Optional<Account> findById(String id);

    Account findByPhoneAndPassword(String phone, String password);

}
