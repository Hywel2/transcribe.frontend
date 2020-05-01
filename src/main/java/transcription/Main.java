package transcription;

import com.github.kiulian.downloader.YoutubeException;
import transcription.windows.MenuFrame;

import java.io.IOException;

public class Main {
        public static void main(String[] args) throws IOException, YoutubeException, InterruptedException {
//            Process p2 = Runtime.getRuntime().exec("python main.py -v");
//            BufferedReader in = new BufferedReader(new InputStreamReader(p2.getInputStream()));
//            String ret= new String(in.readLine());
//            p2.waitFor();
//            System.out.println("returned "+ret);
//            Runtime.getRuntime().
            new MenuFrame();

        }
    }
