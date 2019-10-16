package services;

import providers.ProviderMP3Converter;
import models.Mp3Base64;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Base64;

public class ServiceMP3Converter {

//    private static final Logger LOGGER = Logger.getLogger(ServiceMP3Converter.class);
    private ProviderMP3Converter providerMP3Converter = new ProviderMP3Converter();

    public ServiceMP3Converter() {
        //null
    }

    /**
     * Converts mp4 to mp3 and convert to Base64 code. Cuts the results up into Strings that are 1000 long
     *
     * @param filePath String
     * @throws IOException
     */

    public void convertToBase64(String filePath) throws IOException {

        File mp3 = providerMP3Converter.convert(filePath);
        byte[] bytes = FileUtils.readFileToByteArray(mp3);
        String encoded = Base64.getEncoder().encodeToString(bytes);
        String mpBase64Piece = null;
        encoded = encoded + "*";
        while (encoded.length() >= 1000) {
            mpBase64Piece = encoded.substring(0, 999);
            encoded = encoded.substring(1000, encoded.length() - 1);
            sendHttp(mpBase64Piece);
//            System.out.println(mpBase64Piece);
        }
        if (encoded.length() > 0) {
            sendHttp(encoded);
//            System.out.println(mpBase64Piece);

        }
    }

    /**
     * Sends String section of base64 in http request
     *
     * @param base64Piece String
     * @throws IOException
     */

    public void sendHttp(String base64Piece) throws IOException {
        Mp3Base64 mp3Base64 = new Mp3Base64();
        mp3Base64.setBase64(base64Piece);
        providerMP3Converter.executePost("https://hpldog3e7l.execute-api.eu-west-1.amazonaws.com/default/audibletranscribe",
                mp3Base64);
    }
}
