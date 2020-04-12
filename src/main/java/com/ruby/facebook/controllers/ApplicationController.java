package com.ruby.facebook.controllers;

import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.AdAccountMatchedSearchApplicationsEdgeData;
import com.ruby.facebook.models.Response;
import com.ruby.facebook.services.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/application")
public class ApplicationController {
    private AccountService accountService;

    ApplicationController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public Response<List<AdAccountMatchedSearchApplicationsEdgeData>> getApplications(@RequestParam String appStore) throws APIException {
        var applications = new ArrayList<>(accountService.getAccount().getMatchedSearchApplications()
                .setAppStore(appStore)
                .setQueryTerm("")
                .requestAllFields().execute());
        return new Response<>(applications);
    }
}
