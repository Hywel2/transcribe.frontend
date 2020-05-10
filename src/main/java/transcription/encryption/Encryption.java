package transcription.encryption;

public class Encryption {

    public String encrypt(String data){
        Crypto crypto = new BasicCrypto();
        return new String (crypto.encrypt(data.getBytes()));

    }

    public String decrypt(String data){
        Crypto crypto = new BasicCrypto();
        return new String (crypto.decrypt(data.getBytes()));

    }
}