package com.ruby.facebook.controllers;

import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.APINodeList;
import com.facebook.ads.sdk.SavedAudience;
import com.ruby.facebook.models.Response;
import com.ruby.facebook.services.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/audience")
public class AudienceController {
    private AccountService accountService;

    public AudienceController(AccountService accountService) {

        this.accountService = accountService;
    }

    @GetMapping
    public Response<List<SavedAudience>> getSaved() throws APIException {
        APINodeList<SavedAudience> savedAudiences = accountService.getAccount().getSavedAudiences().requestAllFields().execute();
        return new Response<>(savedAudiences.stream().collect(Collectors.toList()));
    }
}
