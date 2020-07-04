package transcription.services;

import transcription.providers.ProviderDownload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ServiceDownload {
    ProviderDownload providerDownload = new ProviderDownload();

    /**
     * This method sends the request for a download to the provider. It then writes a file using the transcription text
     * that is returned by the server.
     * @param job
     * @param filePath
     */
    private static final Logger LOGGER = Logger.getLogger(ServiceDownload.class.getName());

    public String sendDownloadHttp(String job, String filePath) {
        String json = providerDownload.executeDownloadHttp(job + ".json");
        try {
            Files.write( Paths.get(filePath+"/"+job+".txt"), providerDownload.executeDownloadHttp(job + ".json").getBytes());
            LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.INFO);
            LOGGER.info("complete");
            return json;
        } catch (IOException e) {
            LOGGER.info(e.toString());
            return null;
        }
    }
}
