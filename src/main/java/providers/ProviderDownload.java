package providers;

import Resources.Http;

public class ProviderDownload {

    ProviderUpload providerUpload = new ProviderUpload();

    /**
     * This method prepares the Http object and json to send the http
     * @param jobName
     * @return
     */

    public String sendDownloadHttp(String jobName) {
        Http http = new Http();
        String json;
        json = "{\"mp3Base64\": \" " + "Download" + "hgend" + jobName + " \"}";
        return http.sendPost(json);
    }
}
