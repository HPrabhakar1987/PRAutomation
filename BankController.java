package com.example.bankapi.controller;

import com.example.bankapi.entity.Account;
import com.example.bankapi.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/transfer")
    public String transferFunds(@RequestParam Long fromAccountId, @RequestParam Long toAccountId, @RequestParam Double amount) {
        try {
            bankService.transferFunds(fromAccountId, toAccountId, amount);
            return "Funds transferred successfully.";
        } catch (Exception e) {
            return "Error during funds transfer: " + e.getMessage();
        }
    }
}
