package com.vkbot.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class GsonMapper {

    private static final Gson GSON = new Gson();

    private GsonMapper() {
    }

    public static <T> T deserialize(String json, Type type) {
        return GSON.fromJson(json, type);
    }
}
