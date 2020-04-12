package com.ruby.facebook.controllers;

import com.facebook.ads.sdk.*;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.ruby.facebook.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.HashMap;

@RestController
@RequestMapping("api/account")
public class AccountController {
    private HttpServletRequest servletRequest;
    private Gson gson;

    @Autowired
    public AccountController(HttpServletRequest servletRequest, Gson gson) {
        this.servletRequest = servletRequest;
        this.gson = gson;
    }

    @GetMapping
    public Response<AdAccount[]> getAccounts() throws IOException, InterruptedException, APIException {
        var params = new HashMap<String, Object>();
        var context = new APIContext(servletRequest.getHeader("Authorization"), servletRequest.getHeader("AppSecret"), servletRequest.getHeader("AppId"));
        params.put("access_token", context.getAccessToken());
        params.put("appsecret_proof", context.getAppSecretProof());
        var uri = APIRequest.RequestHelper.constructUrlString(String.join("/", APIConfig.DEFAULT_API_BASE, APIConfig.DEFAULT_API_VERSION, "me/adaccounts"), params);
        HttpRequest request = HttpRequest.newBuilder(URI.create(uri)).GET().build();
        var response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString(Charset.defaultCharset()));

        if (response.statusCode() != 200)
            throw new APIException(null, response.body(), null);

        var jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
        var data = jsonObject.getAsJsonArray("data");
        return new Response<>(gson.fromJson(data, AdAccount[].class));
    }
}
