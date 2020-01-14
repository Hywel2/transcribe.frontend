package providers;

import resources.Http;

public class ProviderUpload {

    /**
     * This method creates a json and Http object to send a http in the upload.
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
