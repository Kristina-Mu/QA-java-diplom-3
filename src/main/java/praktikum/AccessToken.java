package praktikum;

public class AccessToken {
    private String token;
    public AccessToken(String token){
        this.token = token;
    }

    public AccessToken(){
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}