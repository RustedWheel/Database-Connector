package nz.ac.auckland.mysql.connector;

public interface Authenticator {

    Integer authenticate(String username, String password);

}
