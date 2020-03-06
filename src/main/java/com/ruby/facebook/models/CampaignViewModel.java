package com.ruby.facebook.models;

import javax.validation.constraints.NotEmpty;

public class CampaignViewModel {
    @NotEmpty
    private String name;
    private Long dailyBudget;
    private Long spendCap;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDailyBudget() {
        return dailyBudget;
    }

    public void setDailyBudget(Long dailyBudget) {
        this.dailyBudget = dailyBudget;
    }

    public Long getSpendCap() {
        return spendCap;
    }

    public void setSpendCap(Long spendCap) {
        this.spendCap = spendCap;
    }
}
