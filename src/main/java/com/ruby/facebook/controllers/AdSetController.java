package com.ruby.facebook.controllers;

import com.facebook.ads.sdk.*;
import com.ruby.facebook.models.AdSetViewModel;
import com.ruby.facebook.models.Response;
import com.ruby.facebook.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/adset")
public class AdSetController {
    private AccountService accountService;

    @Autowired
    AdSetController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public Response<List<AdSet>> getAdSets() throws APIException {
        var result = accountService.getAccount().getAdSets().execute().stream().collect(Collectors.toList());
        return new Response<>(result);
    }

    @PostMapping
    public AdSet createAdset(@Validated @RequestBody AdSetViewModel model) throws APIException {

        var account = accountService.getAccount();
        var promotedObject = new AdPromotedObject();
        var target = SavedAudience.fetchById(model.getProspectingAudience(), accountService.getContext()).getFieldTargeting();
        var result = account.createAdSet()
                .requestAllFields()
                .setName(model.getName())
                .setCampaignId(model.getCampaignId())
                .setDailyBudget(model.getDailyBudget())
                .setStartTime(model.getStartTime().format(DateTimeFormatter.ISO_DATE_TIME))
                .setEndTime(model.getEndTime().format(DateTimeFormatter.ISO_DATE_TIME))
                .setBillingEvent(model.getBillingEvent())
                .setOptimizationGoal(model.getOptimizationGoal())
                //.setBidAmount(model.getBidAmount())
                .setTargeting(target)
                .setPromotedObject(promotedObject).execute();
        return result;
    }

    @GetMapping("{id}")
    public AdSet getAdSet(@PathVariable String id) throws APIException {
        return AdSet.fetchById(id, accountService.getContext());
    }
}
