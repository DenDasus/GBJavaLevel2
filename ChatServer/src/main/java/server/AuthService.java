package server;

public class AuthService {
    
    static public String getNickByUserNameAndPass(String userName, String pass) {
        if(pass.equals("111")) {
            return null;
        }
        return userName;
    }
}
