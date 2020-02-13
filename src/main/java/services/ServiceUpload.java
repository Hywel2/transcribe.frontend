package services;

import providers.ProviderUpload;
import org.apache.commons.io.FileUtils;
import ws.schild.jave.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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
            byte[] bytes = FileUtils.readFileToByteArray(mp3);
            String mpBase64Piece = Base64.getEncoder().encodeToString(bytes);
            System.out.println("Full base64:" + mpBase64Piece.substring(0,3));
            return cuttingLoop(mpBase64Piece, jobName);
        } catch (Exception e){
            System.out.println("Something isn't right! Error: " + e);
        }
        return null;
    }

    /**
     * This method cuts the base64 into 1000 character pieces, sending each one as a http until it reaches the end tag
     * @param mpBase64Piece
     */
    private String cuttingLoop(String mpBase64Piece, String jobName) {
        mpBase64Piece = "start" + mpBase64Piece;
        Integer numberOfPiecesMinusEnd = (int) Math.ceil( mpBase64Piece.length()/500000.0);
        List<String> base64List = new ArrayList<>();
        for (int i = 0; i<numberOfPiecesMinusEnd-1; i++){
            if (mpBase64Piece.length() >= 500000) {
                base64List.add(mpBase64Piece.substring(0, 500000));
                mpBase64Piece = mpBase64Piece.substring(500000);
            }
            System.out.println(mpBase64Piece.length());
        }
        base64List.add(mpBase64Piece+"end");
        System.out.println(mpBase64Piece.length());
        for (int n = 0; n<base64List.size(); n++){
            if (base64List.get(n) != null) {
//                System.out.println(base64List.get(n));
//                System.out.println(base64List.size()-n);
                System.out.println(n+1 + ":" + base64List.get(n).length());
                String response = providerUpload.executePost(base64List.get(n), jobName);
                System.out.println(response);
            }
        }
        return "Complete";
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
