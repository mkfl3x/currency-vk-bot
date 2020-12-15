package com.vkbot.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourcesLoader {

    public static String getResource(String resourceName) {
        try {
            URI resource = ResourcesLoader.class.getResource(resourceName).toURI();
            return Files.readString(Paths.get(resource));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Resource reading was failed", e);
        }
    }
}
