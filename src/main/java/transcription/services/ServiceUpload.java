package transcription.services;

import org.apache.commons.io.FileUtils;
import transcription.providers.ProviderUpload;
import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaObject;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class ServiceUpload {
    private static final Logger LOGGER = Logger.getLogger(ServiceUpload.class.getName());
    private ProviderUpload providerUpload = new ProviderUpload();
    private static final Pattern VID_ID_PATTERN = Pattern.compile("(?<=v\\=|youtu\\.be\\/)\\w+"),
            MP3_URL_PATTERN = Pattern
                    .compile("(?<=href=\\\")https{0,1}\\:\\/\\/(\\w|\\d){3}\\.ytapivmp3\\.com.+\\.mp3(?=\\\")");

    /**
     * Converts mp4 to mp3 and convert to Base64 code. Uses the ProviderMP3Converter convertToMp3 method to create MP3
     * and sends in http with sendHttp method.
     *
     * @param jobName String
     * @param mp4     File
     * @param email
     * @return
     * @throws IOException
     */

    public String convertToBase64AndSend(String jobName, File mp4, String email) {
        try {
            File mp3 = new File(convertToMp3(mp4.getAbsolutePath()));
            byte[] bytes = FileUtils.readFileToByteArray(mp3);
            String mpBase64Piece = Base64.getEncoder().encodeToString(bytes);
            return cuttingLoop(mpBase64Piece, jobName, email);
        } catch (Exception e) {
            LOGGER.info("Something isn't right! Error: " + e);
        }
        return null;
    }

    /**
     * This method cuts the base64 into 1000 character pieces, sending each one as a http until it reaches the end tag.
     * It sets a tag to the status of start, end or middle, depending on which part of the base64 the code comes from.
     *
     * @param mpBase64Piece
     * @param email
     */
    public String cuttingLoop(String mpBase64Piece, String jobName, String email) {
        String tag;
        String response = null;
        Integer numberOfPiecesMinusEnd = (int) Math.ceil(mpBase64Piece.length() / 500000.0);
        List<String> base64List = new ArrayList<>();
        for (int i = 0; i < numberOfPiecesMinusEnd; i++) {
            if (mpBase64Piece.length() >= 500000) {
                base64List.add(mpBase64Piece.substring(0, 500000));
                mpBase64Piece = mpBase64Piece.substring(500000);
            }
        }
        base64List.add(mpBase64Piece);
        for (int n = 0; n < base64List.size(); n++) {
            if (base64List.get(n) != null) {
                if (n == 0) {
                    tag = "start";
                } else if (n == base64List.size() - 1) {
                    tag = "end";
                } else {
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

    public String convertToMp3(String mp4File) {
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
        } catch (Exception e) {
            LOGGER.info("Something isn't right! Error: " + e);
        }
        return null;
    }

    /**
     * Creates the name of the mp3 file from the path of the mp4 file
     *
     * @param mp4file String
     * @return String
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

    //methods for conversion download of youtube video//

    /**
     * Converts the audio of the youtube to a byte array and writes it to a file to be converted
     * @param httpPath String
     * @param absolutePath String
     * @param jobName String
     * @param email String
     */
    public void getMp4FromYoutube(String httpPath, String absolutePath, String jobName, String email) {
        try {

            byte[] mp3ByteArray = youtubeToMP3(httpPath);
            File mp3File = new File("src/main/resources/audio.mp3");
            OutputStream os = new FileOutputStream(mp3File);
            os.write(mp3ByteArray);
            os.close();

            convertToBase64AndSend(jobName, mp3File, email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method extracts the byteArray from the website using the getID, loadConverter and getMP3URL methods
     * @param youtubeUrl String
     * @return byte[]
     * @throws IOException
     */
    public static byte[] youtubeToMP3(String youtubeUrl) throws IOException {
        String id = getID(youtubeUrl);
        String converter = loadConverter(id);
        String mp3url = getMP3URL(converter);
        byte[] mp3 = load(mp3url);
        return mp3;
    }

    /**
     * This method obtains the ID from the youtubeUrl (the details after the = sign)
     * @param youtubeUrl String
     * @return String
     */
    private static String getID(String youtubeUrl) {
        String id = "";
        boolean equalsFlag = false;
        for (int i = 0; i<youtubeUrl.length(); i++){
            if (youtubeUrl.charAt(i)!='=' && !equalsFlag){
                continue;
            }
            if (youtubeUrl.charAt(i)=='=' && !equalsFlag){
                equalsFlag = true;
                continue;
            }
            id = id + youtubeUrl.charAt(i);
        }
        return id;
    }

    /**
     * This method extracts the converter from the 320 website using the load method
     * @param id String
     * @return String
     * @throws IOException
     */
    private static String loadConverter(String id) throws IOException {
        String url = "https://www.320youtube.com/watch?v=" + id;
        byte[] bytes = load(url);
        return new String(bytes);
    }

    /**
     * This method looks through the converter document for the correct https that will allow the download of
     * the youtube video
     * @param html String
     * @return String
     */
    private static String getMP3URL(String html) {

        String http = "";
        boolean marker = false;
        for (int i = 0; i < html.length(); i++) {
            if (i + 11 > html.length()) {
                return null;
            }
            http = http + html.charAt(i);
            if (html.substring(i, i + 10).equals("https://s0")) {
                http = "h";
                marker = true;
            }
            if (html.charAt(i) == '\"' && marker == true) {
                return http.substring(0, http.length() - 1);
            }
        }
        return null;
    }

    /**
     * This method uses the converter to load the mp3 byte array from the youtub website
     * @param url String
     * @return byte[]t
     * @throws IOException
     */
    private static byte[] load(String url) throws IOException {
        URL url2 = new URL(url);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = url2.openStream();
        byte[] byteChunk = new byte[2500];
        int n;

        while ((n = is.read(byteChunk)) > 0) {
            if (n%10==0) {
                System.out.println("Downloading");
            }
            baos.write(byteChunk, 0, n);
        }

        is.close();
        baos.flush();
        baos.close();

        return baos.toByteArray();
    }
}
