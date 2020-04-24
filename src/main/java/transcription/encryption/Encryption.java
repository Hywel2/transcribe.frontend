package transcription.encryption;

public class Encryption {

    public String encrypt(String data){
        Crypto crypto = new BasicCrypto();
        String encryptedData = new String (crypto.encrypt(data.getBytes()));
        return encryptedData;
    }

    public String decrypt(String data){
        Crypto crypto = new BasicCrypto();
        String encryptedData = new String (crypto.decrypt(data.getBytes()));
        return encryptedData;
    }
}