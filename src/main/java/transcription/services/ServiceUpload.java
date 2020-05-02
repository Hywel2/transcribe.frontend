package transcription.services;

import org.apache.commons.io.FileUtils;
import transcription.providers.ProviderUpload;
import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

public class ServiceUpload {
    private static final Logger LOGGER = Logger.getLogger(ServiceUpload.class.getName());
    private ProviderUpload providerUpload = new ProviderUpload();

    /**
     * Converts mp4 to mp3 and convert to Base64 code. Uses the ProviderMP3Converter convertToMp3 method to create MP3
     * and sends in http with sendHttp method.
     *
     * @param jobName String
     * @param mp4 File
     * @param email
     * @throws IOException
     * @return
     */

    public String convertToBase64AndSend(String jobName, File mp4, String email) {
        try {
            File mp3 = new File(convertToMp3(mp4.getAbsolutePath()));
            byte[] bytes = FileUtils.readFileToByteArray(mp3);
            String mpBase64Piece = Base64.getEncoder().encodeToString(bytes);
            return cuttingLoop(mpBase64Piece, jobName, email);
        } catch (Exception e){
            LOGGER.info("Something isn't right! Error: " + e);
        }
        return null;
    }

    /**
     * This method cuts the base64 into 1000 character pieces, sending each one as a http until it reaches the end tag.
     * It sets a tag to the status of start, end or middle, depending on which part of the base64 the code comes from.
     * @param mpBase64Piece
     * @param email
     */
    public String cuttingLoop(String mpBase64Piece, String jobName, String email) {
        String tag;
        String response = null;
        Integer numberOfPiecesMinusEnd = (int) Math.ceil( mpBase64Piece.length()/500000.0);
        List<String> base64List = new ArrayList<>();
        for (int i = 0; i<numberOfPiecesMinusEnd; i++){
            if (mpBase64Piece.length() >= 500000) {
                base64List.add(mpBase64Piece.substring(0, 500000));
                mpBase64Piece = mpBase64Piece.substring(500000);
            }
        }
        base64List.add(mpBase64Piece);
        for (int n = 0; n<base64List.size(); n++){
            if (base64List.get(n) != null) {
                if (n==0){
                    tag = "start";
                }
                else if (n==base64List.size()-1){
                    tag = "end";
                }
                else {
                    tag = "middle";
                }
                response = providerUpload.executeUploadHttp(base64List.get(n), jobName, tag, email);
            }
        }
        LOGGER.info("complete");
        return response;
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
            LOGGER.info("Something isn't right! Error: " + e);
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

    public void getMp4FromYoutube(String httpPath, String absolutePath, String jobName, String email) {
        try {
            System.out.println(Runtime.getRuntime().exec("ls"));
            Runtime.getRuntime().exec("cd "+ absolutePath);
            Runtime.getRuntime().exec( "youtube-dl "+ httpPath);
            String filePath = getFilePath(httpPath, absolutePath);
            convertToBase64AndSend(jobName, new File(filePath), email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFilePath(String httpPath, String absolutePath){
        String fileEnding=httpPath.substring(httpPath.indexOf('=')+1)+".mp4";
        File[] fileList = new File (absolutePath).listFiles();
        for (File file : fileList){
            if (file.getAbsolutePath().contains(fileEnding)){
                return file.getAbsolutePath();
            }
        }
        return null;
    }
}
