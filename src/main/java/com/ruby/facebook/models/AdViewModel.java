package com.ruby.facebook.models;

public class AdViewModel {
    private String name;
    private String videoId;
    private String thumbnail;
    private long adSetId;
    private String message;
    private String linkDescription;
    private String title;
    private String placePageId;
    private String instagramActorId;
    private String appLink;
    private String appId;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public long getAdSetId() {
        return adSetId;
    }

    public void setAdSetId(long adSetId) {
        this.adSetId = adSetId;
    }

    public String getLinkDescription() {
        return linkDescription;
    }

    public void setLinkDescription(String linkDescription) {
        this.linkDescription = linkDescription;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlacePageId() {
        return placePageId;
    }

    public void setPlacePageId(String placePageId) {
        this.placePageId = placePageId;
    }

    public String getInstagramActorId() {
        return instagramActorId;
    }

    public void setInstagramActorId(String instagramActorId) {
        this.instagramActorId = instagramActorId;
    }

    public String getAppLink() {
        return appLink;
    }

    public void setAppLink(String appLink) {
        this.appLink = appLink;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
