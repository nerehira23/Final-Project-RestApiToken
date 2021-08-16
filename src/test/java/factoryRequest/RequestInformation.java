package factoryRequest;

public class RequestInformation {
    private String url;
    private String body;
    private String token;

    public RequestInformation(String url, String body,String token){
        this.url = url;
        this.body = body;
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
