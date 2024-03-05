package config;

import org.marvel.Constants;

public class TestConfigOld {
    public String getBaseUrl() {
        String baseUrl = System.getProperty("baseUrl", Constants.BASE_URL);
//        String baseUrl = System.getProperty("baseUrl");
//        if (baseUrl == null) {
//            baseUrl = Constants.BASE_URL;
//        }
        return baseUrl;
    }
}
