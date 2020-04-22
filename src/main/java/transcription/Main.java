package transcription;

import transcription.encryption.Encryption;

public class Main {

    public static void main(String[] args) {
//        new MenuFrame();

        Encryption encryption = new Encryption();
        System.out.println(encryption.encrypt("transcribeMp3"));
        String encrypted = encryption.encrypt("transcribeMp3");
        System.out.println(encryption.decrypt(encrypted));
    }
}
