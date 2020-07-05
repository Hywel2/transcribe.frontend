package transcription.encryption;


public class BasicCrypto implements Crypto {

    /**
     * This method decrypts basic information
     * @param data
     * @return
     */
    @Override
    public byte[] decrypt(byte[] data) {
        for (int i = 0; i<data.length; i++){
            data[i] = (byte) (((i % 2)!=0)?data[i] + 1: data [i] - 1);
        }
        return data;
    }
}
