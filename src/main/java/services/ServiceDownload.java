package services;

import providers.ProviderDownload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServiceDownload {
    ProviderDownload providerDownload = new ProviderDownload();

    /**
     * This method sends the request for a download to the provider
     * @param job
     * @param filePath
     */
    public void sendDownloadHttp(String job, String filePath) {
        try {
            Files.write( Paths.get(filePath), providerDownload.sendDownloadHttp(job).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
