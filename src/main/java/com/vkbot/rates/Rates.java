package com.vkbot.rates;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.vkbot.core.PropertiesManager;
import com.vkbot.rates.models.Currency;
import com.vkbot.rates.models.RatesResponse;
import com.vkbot.utils.GsonMapper;
import com.vkbot.utils.Waiter;

import java.io.IOException;
import java.util.concurrent.Executors;

public class Rates {

    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    private static final RatesResponse CACHED_RATES = uploadRates();

    private static volatile boolean needUpdate;

    private static void setUpdate() {
        needUpdate = false;
        Executors.newSingleThreadExecutor().submit(() -> {
            Waiter.wait(Integer.parseInt(PropertiesManager.getProperty("update.time")));
            needUpdate = true;
        });
    }

    private static RatesResponse uploadRates() {
        Request request = new Request.Builder()
                .url("https://www.cbr-xml-daily.ru/daily_json.js")
                .build();
        try {
            Response response = HTTP_CLIENT.newCall(request).execute();
            return GsonMapper.deserialize(response.body().string(), RatesResponse.class);
        } catch (IOException e) {
            throw new RuntimeException("Reaching currency rates was failed", e);
        } finally {
            setUpdate();
        }
    }

    private static RatesResponse getRates() {
        if (needUpdate)
            return uploadRates();
        else
            return CACHED_RATES;
    }

    public static String getRateInfo(String currencyName) {
        Currency currency = getRates().getCurrency(currencyName);
        return String.format("1 RUB = %s %s", currency.getValue(), currency.getCharCode());
    }
}
