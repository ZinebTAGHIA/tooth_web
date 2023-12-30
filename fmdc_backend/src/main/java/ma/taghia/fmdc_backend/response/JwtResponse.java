package ma.taghia.fmdc_backend.response;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private int id;
    private String userName;
    private String role;

    public JwtResponse(String accessToken, int id, String userName, String role) {
        this.token = accessToken;
        this.id = id;
        this.userName = userName;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
