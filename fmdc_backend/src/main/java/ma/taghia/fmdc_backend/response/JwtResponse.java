package ma.taghia.fmdc_backend.response;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private int id;
    private String userName;

    public JwtResponse(String accessToken, int id, String userName) {
        this.token = accessToken;
        this.id = id;
        this.userName = userName;

    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
