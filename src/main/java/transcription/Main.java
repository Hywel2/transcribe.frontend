package transcription;

import transcription.encryption.Encryption;
import transcription.windows.MenuFrame;

public class Main {

    public static void main(String[] args) {
        Encryption encryption = new Encryption();
        System.out.println(encryption.decrypt("isuot90.f`9r60511f/dyddtud.`qh/dvfjs!,xdts.1/`n`{no`xr/bpl0osne.UqbmtbshcdBtugpqjyfq"));
        new MenuFrame();
    }
}
