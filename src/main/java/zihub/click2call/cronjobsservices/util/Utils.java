package zihub.click2call.cronjobsservices.util;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Utils {

    public static String BaseUrl = "";
    public static String WEBSITE_BaseUrl = "";
    public static final MediaType okJSON	= MediaType.parse("application/json; charset=utf-8");
    public interface SERVICES_NAMES {
        String WEBSITE_BASEURL = "https://intacall.com/";
        String BaseUrl = Utils.BaseUrl;
        String NOTIFICATIONS_SERVICE = BaseUrl + "notifications-service/";
        String AUTH_SERVICE = BaseUrl ;
    }


    @NotNull
    public static String makeRequestServices( final String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, okJSON);
        var request =new Request.Builder()
                .url( url)
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        String res = response.body().string();
        response.close();
        return res ;
    }


}
