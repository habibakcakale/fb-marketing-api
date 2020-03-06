package com.ruby.facebook.services;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.AdAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service()
public class AccountService {
    private HttpServletRequest request;

    @Autowired
    AccountService(HttpServletRequest request) {
        this.request = request;
    }
    public AdAccount getAccount() {
        return new AdAccount(request.getHeader("AccountId"), getContext());
    }

    public APIContext getContext() {
        return new APIContext(request.getHeader("Authorization"),request.getHeader("AppSecret"), request.getHeader("AppId"));
    }
}
