package transcription;

import transcription.windows.MenuWindow;

public class Main {
        public static void main(String[] args){
            MenuWindow menuWindow = new MenuWindow();
            menuWindow.setMenuWindow();

//                PythonInterpreter pi = new PythonInterpreter();
//                pi.set("integer", new PyInteger(42));
//                pi.exec("square = integer*integer");
//                PyInteger square = (PyInteger)pi.get("square");
//                System.out.println("square: " + square.asInt());
//
//                PythonInterpreter pi2 = new PythonInterpreter();
//                pi.exec("import sys");
//                pi.exec("sys.path.append('/usr/lib/python2.7')");
//                pi.exec("import youtube_dl");
//
//                pi2.exec("print('Hello Python World!')");
//
//                pi.exec("\n" +
//                        "ydl = youtube_dl.YoutubeDL({'outtmpl': '%(id)s%(ext)s'})");

//    public static void main(String[] args) {
//        try {
////            String clipboardData = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
//            String url = "https://www.youtube.com/watch?v=MDIbGZjXlZg";
//            if (url.startsWith("https://") && !url.contains("\n")) {
//                launchYoutubeDL(url, args);
//            } else {
//                System.out.println("Clipboard does not contain a valid URL: " + url);
//            }
//        } catch (Exception ex) {
//            System.out.println("An exception was thrown, probably because clipboard contained something which we were unable to parse.");
//            System.out.println("The full exception is shown below:");
//            ex.printStackTrace();
//        }
//    }
//
//    public static void launchYoutubeDL(String url, String[] args) throws IOException {
//        Runtime.getRuntime().exec(new String[]
//                {
//                        "cmd", "/k", "start",
//                        "cmd", "/k", "youtube-dl.exe " + url + " " + String.join(" ", args)
//                }
//        );
    }

}