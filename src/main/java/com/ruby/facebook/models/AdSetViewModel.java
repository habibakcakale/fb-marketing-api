package com.ruby.facebook.models;

import com.facebook.ads.sdk.AdSet;

import java.time.LocalDateTime;

public class AdSetViewModel {
    private String campaignId;
    private String name;
    private long dailyBudget;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private AdSet.EnumBillingEvent billingEvent;
    private AdSet.EnumOptimizationGoal optimizationGoal;
    private long bidAmount;
    private String savedAudience;
    private String[] userDevice;
    private String[] userOs;
    private AdSetPromotedObjectViewModel promotedObject;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public long getDailyBudget() {
        return dailyBudget;
    }

    public void setDailyBudget(long dailyBudget) {
        this.dailyBudget = dailyBudget;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public AdSet.EnumBillingEvent getBillingEvent() {
        return billingEvent;
    }

    public void setBillingEvent(AdSet.EnumBillingEvent billingEvent) {
        this.billingEvent = billingEvent;
    }

    public AdSet.EnumOptimizationGoal getOptimizationGoal() {
        return optimizationGoal;
    }

    public void setOptimizationGoal(AdSet.EnumOptimizationGoal optimizationGoal) {
        this.optimizationGoal = optimizationGoal;
    }

    public long getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(long bidAmount) {
        this.bidAmount = bidAmount;
    }

    public String getSavedAudience() {
        return savedAudience;
    }

    public void setSavedAudience(String savedAudience) {
        this.savedAudience = savedAudience;
    }

    public AdSetPromotedObjectViewModel getPromotedObject() {
        return promotedObject;
    }

    public void setPromotedObject(AdSetPromotedObjectViewModel promotedObject) {
        this.promotedObject = promotedObject;
    }

    public String[] getUserDevice() {
        return userDevice;
    }

    public void setUserDevice(String[] userDevice) {
        this.userDevice = userDevice;
    }

    public String[] getUserOs() {
        return userOs;
    }

    public void setUserOs(String[] userOs) {
        this.userOs = userOs;
    }
}
