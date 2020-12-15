package com.vkbot.callback.models;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Payload {

    @SerializedName("button_id")
    private int buttonId;
}
