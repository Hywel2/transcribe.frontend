package transcription;

import resources.Http;

public class Main {

    public static void main(String[] args) {
//        new MenuFrame();

        Http http = new Http();
        http.requestSecret();
    }
}
