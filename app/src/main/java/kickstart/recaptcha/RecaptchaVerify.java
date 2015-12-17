package kickstart.recaptcha;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Vincenz on 17.12.2015.
 */
public class RecaptchaVerify {
    private boolean valid;
    public String recaptchaResponse;

    public boolean recaptchaVerify(String recaptchaResponse, String secret) throws IOException {

        String line;
        String output;

        URL recaptchasite = new URL("https://www.google.com/recaptcha/api/siteverify?secret=" + secret + "&response=" + recaptchaResponse);
        HttpURLConnection connection = (HttpURLConnection) recaptchasite.openConnection();
        connection.setRequestMethod("GET");


        return valid;
    }
}
