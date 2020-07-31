package transcription.services;

import transcription.providers.ProviderDownload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ServiceDownload {
    private static final Logger LOGGER = Logger.getLogger(ServiceDownload.class.getName());
    ProviderDownload providerDownload = new ProviderDownload();
    private String notTrancribed = "File is not transcribed yet.";
    private String notExist = "Job name does not exist";

    /**
     * This method sends the request for a download to the provider. It then writes a file using the transcription text
     * that is returned by the server.
     *
     * @param job String
     * @param filePath String
     * @return String
     */
    public String sendDownloadHttp(String job, String filePath) {
        try {
            String json = providerDownload.executeDownloadHttp(job + ".json");
            if (json.length() > 34 && json.substring(1, 34).equals("java.nio.file.NoSuchFileException")) {
                return notTrancribed;
            }
            if (json.equals("\"File does not exist\"")) {
                return notExist;
            }
            Files.write(Paths.get(filePath + "/" + job + ".txt"), providerDownload.executeDownloadHttp(job + ".json").getBytes());
            LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.INFO);
            LOGGER.info("complete");
            return json;
        } catch (IOException e) {
            LOGGER.info(e.toString());
            return null;
        }
    }
}
