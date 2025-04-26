package com.example.bankapi.service;

import com.example.bankapi.entity.Account;
import com.example.bankapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void transferFunds(Long fromAccountId, Long toAccountId, Double amount) throws Exception {
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new Exception("Source account not found"));

        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new Exception("Destination account not found"));

        if (fromAccount.getBalance() < amount) {
            throw new Exception("Insufficient balance in source account");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
}
