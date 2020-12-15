package com.vkbot.utils;

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GsonMapper {

    private static final Gson GSON = new Gson();

    public static <T> T deserialize(String json, Type type) {
        return GSON.fromJson(json, type);
    }

    public static String serialize(Object object) {
        return GSON.toJson(object);
    }
}