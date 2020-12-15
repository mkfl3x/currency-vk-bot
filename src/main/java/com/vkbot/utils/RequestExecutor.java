package com.vkbot.utils;

import com.vk.api.sdk.client.AbstractQueryBuilder;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestExecutor {

    public static <T, R> R performRequest(AbstractQueryBuilder<T, R> query) {
        try {
            return query.execute();
        } catch (ApiException | ClientException e) {
            throw new RuntimeException("Request execution was failed", e);
        }
    }
}
