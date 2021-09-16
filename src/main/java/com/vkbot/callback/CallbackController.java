package com.vkbot.callback;

import com.vk.api.sdk.objects.callback.messages.CallbackMessage;
import com.vkbot.core.PropertiesManager;
import com.vkbot.utils.GsonMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallbackController {

    private final CallbackHandler callbackHandler = new CallbackHandler(PropertiesManager.getProperty("bot.confirmation"));

    @PostMapping("/callback")
    public String callbackReceive(@RequestBody String message) {
        CallbackMessage callback = GsonMapper.deserialize(message, CallbackMessage.class);
        return callbackHandler.parse(callback);
    }
}
