package com.vkbot.callback;

import com.vk.api.sdk.objects.callback.messages.CallbackMessage;
import com.vkbot.core.PropertiesManager;
import com.vkbot.utils.GsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallbackController {

    private final Logger logger = LoggerFactory.getLogger(CallbackController.class);

    private final CallbackHandler callbackHandler = new CallbackHandler(PropertiesManager.getProperty("bot.confirmation"));

    @PostMapping("/callback")
    public String callbackReceive(@RequestBody String message) {
        CallbackMessage callback = GsonMapper.deserialize(message, CallbackMessage.class);
        logger.info("New event. Type: {}, GroupID: {}", callback.getType(), callback.getGroupId());
        return callbackHandler.parse(callback);
    }
}
