package com.ruby.facebook.controllers;

import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.Application;
import com.ruby.facebook.models.Response;
import com.ruby.facebook.services.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/application")
public class ApplicationController {
    private AccountService accountService;

    ApplicationController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public Response<List<Application>> getApplications() throws APIException {
        var applications = accountService.getAccount().getApplications()
                .requestAllFields().execute().stream().collect(Collectors.toList());
        return new Response<>(applications);
    }
}
