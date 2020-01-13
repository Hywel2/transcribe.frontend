package providers;

import Resources.Http;
import ws.schild.jave.*;

import java.io.*;

public class ProviderUpload {

    /**
     * This method prepares the upload json and Http object to send the http
     *
     * @param mp3Base64
     * @return
     * @throws Exception
     */

    public String executePost(String mp3Base64, String jobName) {
        Http http = new Http();
        String json;
        json = "{\"mp3Base64\": \" " + mp3Base64 + "hgend" + jobName + " \"}";
        return http.sendPost(json);
    }
}
