package utils;

import java.net.MalformedURLException;
import java.net.URL;

public class URLBuilder {
    private String newURL;

    public URLBuilder addBaseURL(String baseURL) {
        newURL = baseURL;
        return this;
    }

    public URLBuilder addToPath(String str) {
        newURL = newURL + "/" + str;
        return this;
    }

    public URL build() throws MalformedURLException {
        String builtURL = newURL;
        resetURL();
        return new URL(builtURL);
    }

    private void resetURL() {
        newURL = "";
    }
}