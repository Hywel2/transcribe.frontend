package transcription.providers;

import transcription.resources.Http;

public class ProviderUpload {

    /**
     * This method creates a json and Http object to send a http in the upload. A tag in the json shows if the base64
     * is at the start, middle or end. Finally, the user's email is sent  in the json.
     *
     * @param mp3Base64 String
     * @param tag String
     * @param email String
     * @return String
     * @throws Exception
     */
    public String executeUploadHttp(String mp3Base64, String jobName, String tag, String email) {
        String json = ("{\"mp3Base64\":\"" + mp3Base64 + "hgend" + jobName + "\", \"tag\":\"" + tag + "\",\"email\":\"" + email + "\"}");
        return new Http().sendPost(json);
    }
}
