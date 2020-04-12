package com.ruby.facebook.controllers;

import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.AdVideo;
import com.ruby.facebook.models.Response;
import com.ruby.facebook.services.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/video")
public class VideoController {
    private AccountService accountService;

    public VideoController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public Response<List<AdVideo>> getAllVideos() throws APIException {
        var videos = new ArrayList<>(accountService.getAccount().getAdVideos()
                .requestIdField()
                .requestFormatField()
                .requestIconField()
                .execute());
        return new Response<>(videos);
    }
}
