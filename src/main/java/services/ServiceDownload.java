package services;

import org.apache.log4j.Logger;
import providers.ProviderDownload;

public class ServiceDownload {

    private static final Logger LOGGER = Logger.getLogger(ServiceDownload.class);
    ProviderDownload providerDownload = new ProviderDownload();

    /**
     * This method uses the provider to send the http
     * @param job
     */
    public void sendDownloadHttp(String job) {
        LOGGER.info(providerDownload.sendDownloadHttp(job));
    }
}
