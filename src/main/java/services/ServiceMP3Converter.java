package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import providers.ProviderMP3Converter;
import models.Mp3Base64;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

public class ServiceMP3Converter {

    private static final Logger LOGGER = Logger.getLogger(ServiceMP3Converter.class);
    private ProviderMP3Converter providerMP3Converter = new ProviderMP3Converter();

    public ServiceMP3Converter() {
        //null
    }

    /**
     * Converts mp4 to mp3 and convert to Base64 code. Puts the resulting value in an Mp3Base64 object ready to be sent
     * @param filePath
     * @throws IOException
     */

    public void sendValue(String filePath) throws IOException {

        File mp3 = providerMP3Converter.convert(filePath);
        byte[] bytes = FileUtils.readFileToByteArray(mp3);
        String encoded = Base64.getEncoder().encodeToString(bytes);
        Mp3Base64 mp3Base64 = new Mp3Base64();
        mp3Base64.setBase64(encoded);
        providerMP3Converter.executePost("https://43w6pe3k11.execute-api.eu-west-1.amazonaws.com/prod/AudibleTranscribe?base64="+encoded,
                mp3Base64);

    }

}
