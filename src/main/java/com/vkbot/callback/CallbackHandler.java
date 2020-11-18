package com.vkbot.callback;

import com.vk.api.sdk.callback.CallbackApi;
import com.vk.api.sdk.objects.messages.Message;
import com.vkbot.core.BotActions;

public class CallbackHandler extends CallbackApi {

    private final BotActions actions = new BotActions();

    @Override
    public void messageNew(Integer groupId, Message message) {
        actions.sendMessage(356234071, "hello");
    }
}