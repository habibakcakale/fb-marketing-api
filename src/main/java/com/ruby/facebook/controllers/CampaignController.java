package com.ruby.facebook.controllers;

import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.AdSet;
import com.facebook.ads.sdk.Campaign;
import com.ruby.facebook.models.CampaignViewModel;
import com.ruby.facebook.models.Response;
import com.ruby.facebook.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/campaign")
public class CampaignController {

    private AccountService accountService;

    @Autowired
    public CampaignController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public Response<List<Campaign>> getCampaigns() throws APIException {
        var campaignsResult = accountService.getAccount().getCampaigns()
                .requestAllFields().execute();
        var result = campaignsResult.stream().collect(Collectors.toList());
        return new Response<>(result);
    }

    @GetMapping("{id}")
    public Campaign getCampaign(@PathVariable String id) throws APIException {
        Campaign campaign = Campaign.fetchById(id, accountService.getContext());
        return campaign;


    }

    @DeleteMapping("{id}")
    public Campaign deleteCampaign(@PathVariable String id) throws APIException {
        Campaign campaign = Campaign.fetchById(id, accountService.getContext());
        campaign.delete().execute();
        return campaign;
    }

    @GetMapping("{id}/adsets")
    public Response<List<AdSet>> getAdSets(@PathVariable String id) throws APIException {
        var list = Campaign.fetchById(id, accountService.getContext())
                .getAdSets()
                .requestAllFields()
                .requestInstagramActorIdField(false)
                .execute()
                .stream()
                .collect(Collectors.toList());
        return new Response<>(list);
    }

    @PostMapping
    public Response<Campaign> createCampaign(@Validated @RequestBody CampaignViewModel newCampaign) throws APIException {
        var campaign = accountService.getAccount().createCampaign()
                .setName(newCampaign.getName())
                .setBidStrategy(Campaign.EnumBidStrategy.VALUE_LOWEST_COST_WITHOUT_CAP)
                .setObjective(Campaign.EnumObjective.VALUE_APP_INSTALLS)
                .setStatus(Campaign.EnumStatus.VALUE_PAUSED)
                .setDailyBudget(newCampaign.getDailyBudget())
                .setSpecialAdCategory(Campaign.EnumSpecialAdCategory.VALUE_NONE)
                .requestAllFields();
        if (newCampaign.getSpendCap() != null)
            campaign.setSpendCap(newCampaign.getSpendCap());
        var id = campaign.execute().getId();
        return new Response<>(Campaign.fetchById(id, accountService.getContext()));
    }
}