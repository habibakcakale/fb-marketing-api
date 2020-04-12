package com.ruby.facebook.controllers;

import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.AdPromotedObject;
import com.facebook.ads.sdk.AdSet;
import com.facebook.ads.sdk.SavedAudience;
import com.ruby.facebook.models.AdSetViewModel;
import com.ruby.facebook.models.Response;
import com.ruby.facebook.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        var result = new ArrayList<>(accountService.getAccount()
                .getAdSets()
                .requestAllFields()
                .requestField("full_funnel_exploration_mode", false)//(#10) The ad account and app id must be whitelisted error occurs.
                .execute());
        return new Response<>(result);
    }

    @GetMapping("{id}")
    public AdSet getAdSet(@PathVariable String id) throws APIException {
        return AdSet.fetchById(id, accountService.getContext());
    }

    @PostMapping
    public AdSet createAdset(@Validated @RequestBody AdSetViewModel model) throws APIException {

        var account = accountService.getAccount();
        var promotedObject = new AdPromotedObject()
                .setFieldApplicationId(model.getPromotedObject().getApplicationId())
                .setFieldObjectStoreUrl(model.getPromotedObject().getStoreUrl());
        var savedAudience = SavedAudience.fetchById(model.getSavedAudience(), accountService.getContext());
        var target = savedAudience.getFieldTargeting();
        target.setFieldUserDevice(Arrays.asList(model.getUserDevice()))
                .setFieldUserOs(Arrays.asList(model.getUserOs()));

        return account.createAdSet()
                .requestAllFields()
                .setName(model.getName())
                .setCampaignId(model.getCampaignId())
                //.setDailyBudget(model.getDailyBudget())
                .setStartTime(model.getStartTime().format(DateTimeFormatter.ISO_DATE_TIME))
                .setEndTime(model.getEndTime().format(DateTimeFormatter.ISO_DATE_TIME))
                .setBillingEvent(model.getBillingEvent())
                .setOptimizationGoal(model.getOptimizationGoal())
                //.setBidAmount(model.getBidAmount())
                .setTargeting(target)
                .setBidStrategy(AdSet.EnumBidStrategy.VALUE_LOWEST_COST_WITHOUT_CAP)
                .setStatus(AdSet.EnumStatus.VALUE_PAUSED)
                .setPromotedObject(promotedObject).execute();
    }


}
