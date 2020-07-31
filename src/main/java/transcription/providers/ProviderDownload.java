package transcription.providers;

import transcription.resources.Http;

public class ProviderDownload {

    /**
     * This method creates the json and the Http object that will be used to send the http request for the download.
     * A tag in the Json shows that the http is a download.
     * @param jobName String
     * @return String
     */
    public String executeDownloadHttp(String jobName) {
        String json = ("{\"mp3Base64\":\"" + "Download" + "hgend" + jobName + "\", \"tag\":\"" + "NA" + "\"}");
        return new Http().sendPost(json);
    }
}