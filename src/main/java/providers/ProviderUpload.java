package providers;

import resources.Http;

public class ProviderUpload {

    /**
     * This method creates a json and Http object to send a http in the upload. A tag in the json shows if the base64
     * is at the start, middle or end.
     *
     * @param mp3Base64
     * @param tag
     * @return
     * @throws Exception
     */

    public String executePost(String mp3Base64, String jobName, String tag) {
        Http http = new Http();
        String json;
        json = ("{\"mp3Base64\":\"" + mp3Base64 + "hgend" + jobName + "\", \"tag\":\"" + tag + "\"}");
        return http.sendPost(json);
    }
}
