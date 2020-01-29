package services;

import providers.ProviderUpload;
import org.apache.commons.io.FileUtils;
import resources.Http;
import ws.schild.jave.*;

import java.io.*;
import java.util.Base64;

public class ServiceUpload {
    private ProviderUpload providerUpload = new ProviderUpload();

    /**
     * Converts mp4 to mp3 and convert to Base64 code. Uses the ProviderMP3Converter convertToMp3 method to create MP3
     * and sends in http with sendHttp method.
     *
     * @param filePath String
     * @throws IOException
     * @return
     */

    public String convertToBase64AndSend(String filePath, String jobName) {
        try {
            File mp3 = new File(convertToMp3(filePath));
            byte[] mpBase64Piece = FileUtils.readFileToByteArray(mp3);

//            byte[] bytes = FileUtils.readFileToByteArray(mp3);
//            System.out.println(bytes.length);
//            String mpBase64Piece = Base64.getEncoder().encodeToString(bytes);
//            System.out.println(mpBase64Piece);
//            System.out.println(mpBase64Piece.length());
//            return providerUpload.executePost(mpBase64Piece, jobName);
            Http http = new Http();
            http.sendPost(mpBase64Piece);
        } catch (Exception e){
            System.out.println("Something isn't right! Error: " + e);
        }
        return null;
    }

    /**
     * Converts the mp4 file into the mp3 file
     *
     * @param mp4File
     * @return
     */

    public String convertToMp3(String mp4File){
        try {
            File source = new File(mp4File);
            File target = new File(createMp3(mp4File));

            //Audio Attributes
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("libmp3lame");
            audio.setBitRate(128000);
            audio.setChannels(2);
            audio.setSamplingRate(44100);

            //Encoding attributes
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("mp3");
            attrs.setAudioAttributes(audio);

            //Encode
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(source), target, attrs);

            return createMp3(mp4File);
        } catch (Exception e){
            System.out.println("Something isn't right! Error: " + e);
        }
        return null;
    }

    /**
     * Creates the name of the mp3 file from the path of the mp4 file
     *
     * @param mp4file
     * @return
     */

    public String createMp3(String mp4file) {
        Character character = mp4file.charAt(mp4file.length() - 1);
        while (!character.equals('/')) {
            mp4file = mp4file.substring(0, mp4file.length() - 1);
            character = mp4file.charAt(mp4file.length() - 1);
        }
        mp4file = mp4file + "Audio.mp3";
        return mp4file;
    }
}
