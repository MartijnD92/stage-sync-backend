package nl.stagesync.stagesync.exception;

public class UsernameNotFoundException extends RuntimeException {

    public UsernameNotFoundException(String username) {
        super("Cannot find user " + username);
    }

}
