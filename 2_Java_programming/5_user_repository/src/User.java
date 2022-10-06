import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Very simple User for OOP excersize.
 * 
 * @author Ben Bright <nooc@users.noreply.github.com>
 */
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        setPassword(password);
    }

    void authenticate(String password) {
        if (hashPassword(password).equals(this.password)) {
            System.out.format("%s successfully logged in.\n", username);
        } else {
            System.out.format("Invalid password for %s.\n", username);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    private String hashPassword(String password) {
        try {
            var bytes = MessageDigest.getInstance("MD5").digest(password.getBytes(StandardCharsets.UTF_8));
            return new BigInteger(1, bytes).toString(16);
        } catch (NoSuchAlgorithmException e) {
            return password;
        }
    }
}
