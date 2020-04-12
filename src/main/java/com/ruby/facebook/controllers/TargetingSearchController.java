package com.ruby.facebook.controllers;

import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.AdAccountTargetingUnified;
import com.ruby.facebook.services.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/targetingsearch")
public class TargetingSearchController {
    private AccountService accountService;

    public TargetingSearchController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AdAccountTargetingUnified> search() throws APIException {
        var account = accountService.getAccount();
        return new ArrayList<>(account.getTargetingSearch().requestAllFields().execute());
    }
}
