package transcription.encryption;

public class Encryption {

    /**
     * This method uses the BasicCrypto to encrypt information
     * @param data
     * @return
     */
    public String decrypt(String data){
        Crypto crypto = new BasicCrypto();
        return new String (crypto.decrypt(data.getBytes()));

    }
}