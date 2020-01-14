package services;

import providers.ProviderDownload;

public class ServiceDownload {
    ProviderDownload providerDownload = new ProviderDownload();

    /**
     * This method sends the request for a download to the provider
     * @param job
     */
    public void sendDownloadHttp(String job) {
        System.out.println(providerDownload.sendDownloadHttp(job));
    }
}
