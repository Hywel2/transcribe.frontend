package services;

import providers.ProviderDownload;

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
    public boolean sendDownloadHttp(String job, String filePath) {

        try {
            Files.write( Paths.get(filePath+"/"+job+".txt"), providerDownload.sendDownloadHttp(job + ".json").getBytes());
            LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.INFO);
            LOGGER.info("complete");
            return true;
        } catch (IOException e) {
            LOGGER.info(e.toString());
            return false;
        }
    }
}
