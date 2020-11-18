package com.vkbot.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

    private static final Properties PROPS;

    static {
        PROPS = new Properties();
        try {
            PROPS.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Reading properties was failed", e);
        }
    }

    private PropertiesManager() {
    }

    public static String getProperty(String key) {
        final String propertyValue = PROPS.getProperty(key);
        if (propertyValue.isEmpty()) {
            throw new RuntimeException(String.format("{%s} property is empty", key));
        }
        return propertyValue;
    }
}
