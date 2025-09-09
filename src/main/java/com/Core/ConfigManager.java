package com.Core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties;
    private static final String CONFIG_FILE = "config.properties";

    static {
        properties = new Properties();
        try {
            FileInputStream input = new FileInputStream(CONFIG_FILE);
            properties.load(input);
            input.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    public static int getTimeout() {
        return Integer.parseInt(getProperty("timeout"));
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless"));
    }
    
 // Add these methods to your ConfigManager.java
    public static boolean shouldTakeScreenshots() {
        return Boolean.parseBoolean(getProperty("screenshot.on.failure", "true"));
    }

    public static String getScreenshotDirectory() {
        return getProperty("screenshot.dir", "target/screenshots/");
    }

    // Update the getProperty method to handle defaults
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

}