package com.vkbot.core;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.Keyboard;
import com.vkbot.utils.GsonMapper;
import com.vkbot.utils.ResourcesLoader;

import java.util.Random;

import static com.vkbot.utils.RequestExecutor.performRequest;

public class BotActions {

    private final VkApiClient vk = new VkApiClient(new HttpTransportClient());

    private final GroupActor group = new GroupActor(
            Integer.parseInt(PropertiesManager.getProperty("bot.id")),
            PropertiesManager.getProperty("bot.token")
    );

    public void sendMessage(int peerId, String text) {
        performRequest(
                vk.messages().send(group)
                        .peerId(peerId)
                        .message(text)
                        .randomId(new Random().nextInt())
        );
    }

    public void sendKeyboard(int peerId) {
        Keyboard keyboard = GsonMapper.deserialize(
                ResourcesLoader.getResource("/keyboard.json"),
                Keyboard.class
        );
        performRequest(
                vk.messages().send(group)
                        .peerId(peerId)
                        .message("this is required shit")
                        .keyboard(keyboard)
                        .randomId(new Random().nextInt())
        );
    }
}
