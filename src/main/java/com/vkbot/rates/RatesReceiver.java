package com.vkbot.rates;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.vkbot.rates.models.RatesResponse;
import com.vkbot.utils.GsonMapper;

import java.io.IOException;

public class RatesReceiver {

    private final OkHttpClient httpClient = new OkHttpClient();

    public RatesResponse getRates() {
        // TODO: add cache
        Request request = new Request.Builder()
                .url("https://www.cbr-xml-daily.ru/daily_json.js")
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            return GsonMapper.deserialize(response.body().string(), RatesResponse.class);
        } catch (IOException e) {
            throw new RuntimeException("Reaching currency rates was failed", e);
        }
    }
}
