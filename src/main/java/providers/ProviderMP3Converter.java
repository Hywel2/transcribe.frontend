package providers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Mp3Base64;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tunde Michael
 */

public class ProviderMP3Converter {

    private static final Logger LOG = Logger.getLogger(ProviderMP3Converter.class.getName());

    /**
     * Converts the mp4 file into the mp3 file
     *
     * @param mp4File
     * @return
     */

    public File convert(String mp4File) {

        try {
            String line;
            //Creates mp3 audio file path
            String mp3File = createMp3(mp4File);
            String cmd = "ffmpeg -i " + mp4File + " " + mp3File;
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getErrorStream()));
            while ((line = in.readLine()) != null) {
            }
            p.waitFor();
            in.close();
            File returnFile = new File(mp3File);
            return returnFile;
        } catch (IOException | InterruptedException e) {
            LOG.log(Level.SEVERE, null, e);
        }
        return null;
    }

    /**
     * Creates the name of the mp3 file from the path of the mp4 file
     *
     * @param mp3File
     * @return
     */

    public String createMp3(String mp3File) {
        Character character = new Character(mp3File.charAt(mp3File.length() - 1));
        while (!character.equals('/')) {
            mp3File = mp3File.substring(0, mp3File.length() - 2);
            character = new Character(mp3File.charAt(mp3File.length() - 1));
        }
        mp3File = mp3File + "Audio.mp3";
        return mp3File;
    }

    /**
     * This method sends a HTTP request to the amazon AWS lambda with the base64
     * NB: This is not tested!
     */


    public String executePost(String targetURL, Mp3Base64 mp3Base64) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String mp3Base64Json = mapper.writeValueAsString(mp3Base64);
        URL obj = new URL(targetURL);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");

        connection.setRequestProperty("Content-Length",
                Integer.toString(mp3Base64Json.getBytes().length));
        connection.setRequestProperty("Content-Language", "en-US");

        connection.setUseCaches(false);
        connection.setDoOutput(true);

        //Add authorizer
        connection.setRequestProperty("key","value");


        //Send request
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes("base64=" + mp3Base64Json);
        wr.flush();
        wr.close();

        //Get Response
        int responseCode = connection.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + targetURL);
        System.out.println("Post parameters : base64 =" + mp3Base64Json);
        System.out.println("Response Code : " + responseCode);
        InputStream is = connection.getInputStream();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
        return response.toString();
    }
}

