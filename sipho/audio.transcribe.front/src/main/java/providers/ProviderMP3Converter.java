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
 *
 * @author Tunde Michael
 *
 */

public class ProviderMP3Converter {

    private static final Logger LOG = Logger.getLogger(ProviderMP3Converter.class.getName());

    /**
     * Converts the mp4 file into the mp3 file
     * @param mp4File
     * @return
     */

    public File convert(String mp4File){

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
            File returnFile = new File (mp3File);
            return returnFile;
        } catch (IOException | InterruptedException e) {
            LOG.log(Level.SEVERE, null, e);
        }
        return null;
    }

    /**
     * Creates the name of the mp3 file from the path of the mp4 file
     * @param mp3File
     * @return
     */

    public String createMp3(String mp3File){
        Character character = new Character(mp3File.charAt(mp3File.length()-1));
        while (!character.equals('/')) {
            mp3File = mp3File.substring(0, mp3File.length()-2);
            character = new Character(mp3File.charAt(mp3File.length()-1));
        }
        mp3File = mp3File+"Audio.mp3";
        return mp3File;
    }

    /**
     * This method sends a HTTP request to the amazon AWS lambda with the base64
     * NB: This is not tested!
     */

    public String executePost(String targetURL, Mp3Base64 mp3Base64) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String mp3Base64Json = mapper.writeValueAsString(mp3Base64);
        HttpURLConnection connection = null;

        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length",
                    Integer.toString(mp3Base64Json.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.writeBytes(mp3Base64Json);
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }



}