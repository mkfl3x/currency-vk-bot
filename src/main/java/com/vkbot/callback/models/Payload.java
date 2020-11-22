package com.vkbot.callback.models;

import com.google.gson.annotations.SerializedName;

public class Payload {

    @SerializedName("button_id")
    private int buttonId;

    public int getButtonId() {
        return this.buttonId;
    }
}
