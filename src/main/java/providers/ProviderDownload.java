package providers;

import resources.Http;

public class ProviderDownload {

    /**
     * This method creates the json and the Http object that will be used to send the http request for the download
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
