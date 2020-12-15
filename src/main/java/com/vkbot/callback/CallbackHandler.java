package com.vkbot.callback;

import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.MessageAttachment;
import com.vkbot.callback.models.Payload;
import com.vkbot.core.BotActions;
import com.vkbot.rates.Rates;
import com.vkbot.utils.GsonMapper;

import java.util.List;

public class CallbackHandler {

    private final BotActions actions = new BotActions();

    private void attachmentsHandling(int peerId, List<MessageAttachment> attachments) {
        actions.sendMessage(peerId, "Attachments handling is not supported");
    }

    private void payloadHandling(int peerId, String payload) {
        Payload buttonCase = GsonMapper.deserialize(payload, Payload.class);
        switch (buttonCase.getButtonId()) {
            case 1:
                actions.sendMessage(peerId, Rates.getRateInfo("USD"));
                break;
            case 2:
                actions.sendMessage(peerId, Rates.getRateInfo("EUR"));
                break;
            default:
                throw new RuntimeException("Unrecognized button_id was tried to use");
        }
    }

    public void newMessage(Message message) {
        if (message.getPayload() != null)
            payloadHandling(message.getPeerId(), message.getPayload());
        if (!message.getAttachments().isEmpty()) {
            attachmentsHandling(message.getPeerId(), message.getAttachments());
        }
        actions.sendKeyboard(message.getPeerId());
    }
}