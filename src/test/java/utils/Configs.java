package utils;

import java.io.IOException;
import java.util.Properties;

public class Configs {
    private static Properties propertiesAuth;
    private static Properties propertiesURL;

    private Configs() { }

    public static String getAuthValueByKey(String key) {
        if (propertiesAuth == null) {
            try {
                propertiesAuth = new Properties();
                propertiesAuth.load(Configs.class.getResourceAsStream("/userauthentication.properties"));
            } catch (IOException e) {
            }
        }
        return propertiesAuth.get(key).toString();
    }

    public static String getURLValueByKey(String key) {
        if (propertiesURL == null) {
            try {
                propertiesURL = new Properties();
                propertiesURL.load(Configs.class.getResourceAsStream("/trellourls.properties"));
            } catch (IOException e) {
            }
        }
        return propertiesURL.get(key).toString();
    }
}