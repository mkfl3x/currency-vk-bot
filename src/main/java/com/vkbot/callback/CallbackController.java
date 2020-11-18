package com.vkbot.callback;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vkbot.core.PropertiesManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallbackController {

    private final CallbackHandler handler = new CallbackHandler();

    @PostMapping("/callback")
    public String callbackReceive(@RequestBody String message) { // TODO: Use CallbackMessage instead String
        JsonObject callback = new Gson().fromJson(message, JsonObject.class);
        if (callback.get("type").getAsString().equals("confirmation")) {
            return PropertiesManager.getProperty("bot.confirmation");
        } else {
            handler.parse(callback);
        }
        return "ok";
    }

    // TODO: only for debug - remove it
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
