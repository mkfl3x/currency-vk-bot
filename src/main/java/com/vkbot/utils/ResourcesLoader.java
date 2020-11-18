package com.vkbot.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourcesLoader {

    private ResourcesLoader() {
    }

    public static String getResource(String resourceName) {
        try {
            URI resource = ResourcesLoader.class.getResource(resourceName).toURI();
            return Files.readString(Paths.get(resource));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Resource reading was failed", e);
        }
    }
}
