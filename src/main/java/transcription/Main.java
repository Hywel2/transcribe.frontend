package transcription;

import transcription.windows.InterfaceWindow;

public class Main {

    public static void main(String[] args) {
        InterfaceWindow interfaceWindow = new InterfaceWindow();
        interfaceWindow.setInterfaceWindow();
    }

//    private void validate(String userName, String userPassword) {
//        if ((userName.equals("Admin")) && (userPassword.equals("1234"))) {
//            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//            startActivity(intent);
//        } else {
//            counter--;
//            Info.setText("No. of attempts remaining:" + String.valueOf(counter));
//        }
//    }
}