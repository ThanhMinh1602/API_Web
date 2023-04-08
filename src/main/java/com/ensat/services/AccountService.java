package com.ensat.services;

import com.ensat.entities.Account;
import com.ensat.entities.Category;
import com.ensat.entities.Role;
import com.ensat.repositories.AccountRepository;
import com.ensat.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> listAll() {
        return accountRepository.findAll();
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public Account get(Integer uID) {
        return accountRepository.findById(uID).get();
    }

    public void delete(Integer uID) {
        accountRepository.deleteById(uID);
    }

    public Account createAccount(Account account) {
        Role defaultRole = new Role(2, "user");
        account.setRole(defaultRole);
        String user = account.getUser();
        Account existingAccount = accountRepository.findByUser(user);
        if (existingAccount != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tên tài khoản đã tồn tại");
        }
        return accountRepository.save(account);
    }

    public Account findByUser(String user) {
        return accountRepository.findByUser(user);
    }

    public boolean changePassword(Account account, String oldPassword, String newPassword) {
        if (account.getPass().equals(oldPassword)) {
            account.setPass(newPassword);
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    public Account findById(Integer uID) {
        return accountRepository.findById(uID).orElse(null);
    }

}
