package models;

public class Response {

    private String base64;

    public Response(String base64) {
        this.base64 = base64;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    @Override
    public String toString() {
        return "Mp3Base64{" +
                "base64='" + base64 + '\'' +
                '}';
    }
}