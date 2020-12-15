package com.vkbot.callback;

import com.google.gson.JsonObject;
import com.vk.api.sdk.objects.callback.messages.CallbackMessage;
import com.vk.api.sdk.objects.messages.Message;
import com.vkbot.core.PropertiesManager;
import com.vkbot.utils.GsonMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;

@RestController
public class CallbackController {

    private final CallbackHandler callbackHandler = new CallbackHandler();

    // Necessary step because SDK models are not accordant to callback objects.
    // At first we need to extract specific object from wrapper object (like "message")
    private <T> T getCallbackObject(Object object, Type objectType) {
        JsonObject payload = GsonMapper.deserialize(GsonMapper.serialize(object), JsonObject.class);
        return GsonMapper.deserialize(payload.get("message").toString(), objectType);
    }

    @PostMapping("/callback")
    public String callbackReceive(@RequestBody String message) {
        CallbackMessage callback = GsonMapper.deserialize(message, CallbackMessage.class);
        switch (callback.getType()) {
            case CONFIRMATION:
                return PropertiesManager.getProperty("bot.confirmation");
            case MESSAGE_NEW:
                callbackHandler.newMessage(getCallbackObject(callback.getObject(), Message.class));
                break;
            default:
                throw new RuntimeException("Unexpected callback event type: " + callback.getType());
        }
        return "ok";
    }
}
