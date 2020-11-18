package com.vkbot.core;

import com.vk.api.sdk.client.AbstractQueryBuilder;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

import java.util.Random;

public class BotActions {

    private final VkApiClient vk = new VkApiClient(new HttpTransportClient());

    private final GroupActor group = new GroupActor(
            Integer.parseInt(PropertiesManager.getProperty("bot.id")),
            PropertiesManager.getProperty("bot.token")
    );

    private <T, R> R executeRequest(AbstractQueryBuilder<T, R> query) {
        try {
            return query.execute();
        } catch (ApiException | ClientException e) {
            throw new RuntimeException("Request execution was failed", e);
        }
    }

    public void sendMessage(int peerId, String text) {
        executeRequest(
                vk.messages().send(group)
                        .peerId(peerId)
                        .message(text)
                        .randomId(new Random().nextInt())
        );
    }
}
