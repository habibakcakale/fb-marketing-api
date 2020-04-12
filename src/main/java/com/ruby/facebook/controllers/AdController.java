package com.ruby.facebook.controllers;

import com.facebook.ads.sdk.*;
import com.ruby.facebook.models.AdViewModel;
import com.ruby.facebook.models.Response;
import com.ruby.facebook.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/ad")
public class AdController {
    private AccountService accountService;

    public AdController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public Response<List<Ad>> getAds() throws APIException {
        var result = new ArrayList<>(accountService.getAccount().getAds().requestAllFields().execute());
        return new Response<>(result);
    }

    @PostMapping
    public Response<Ad> create(@RequestBody AdViewModel adViewModel) throws APIException {
        var thumbs = new AdVideo(adViewModel.getVideoId(), accountService.getContext()).getThumbnails().requestAllFields().execute();
        var thumb = thumbs.stream().findFirst().or(() -> Optional.of(new VideoThumbnail())).get();
        var ad = accountService.getAccount()
                .createAd()
                .setName(adViewModel.getName())
                .setStatus(Ad.EnumStatus.VALUE_PAUSED)
                .setCreative(new AdCreative()
                        .setFieldObjectType(AdCreative.EnumObjectType.VALUE_VIDEO)
                        .setFieldObjectStorySpec(new AdCreativeObjectStorySpec()
                                .setFieldPageId(adViewModel.getPlacePageId())
                                .setFieldInstagramActorId(adViewModel.getInstagramActorId())
                                .setFieldVideoData(new AdCreativeVideoData()
                                        //.setFieldTitle(adViewModel.getTitle())
                                        //.setFieldLinkDescription(adViewModel.getLinkDescription())
                                        .setFieldMessage(adViewModel.getMessage())
                                        .setFieldVideoId(adViewModel.getVideoId())
                                        .setFieldImageUrl(thumb.getFieldUri())
                                        .setFieldCallToAction(new AdCreativeLinkDataCallToAction()
                                                .setFieldType(AdCreativeLinkDataCallToAction.EnumType.VALUE_INSTALL_MOBILE_APP)
                                                .setFieldValue(new AdCreativeLinkDataCallToActionValue()
                                                        .setFieldLink(adViewModel.getAppLink())
                                                        .setFieldApplication(adViewModel.getAppId())
                                                )
                                        )
                                )
                        )
                )
                .setAdsetId(adViewModel.getAdSetId())
                .requestAllFields()
                .execute();
        return new Response<>(ad);
    }
}
