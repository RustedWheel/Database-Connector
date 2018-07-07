package nz.ac.auckland.mysql.connector;

public class GoogleAuthenticator implements Authenticator {

    @Override
    public Integer authenticate(String username, String password) {
        return 200;
    }
}
