package space.nixus.userbuilder.builder;

import java.security.InvalidParameterException;
import space.nixus.userbuilder.UserImpl;
import space.nixus.userbuilder.model.IUser;

public class UserBuilder {

    private String userName;
    private String email;
    private String displayName;

    public UserBuilder() {}

    private void checkEmpty(String value, String label) {
        if(value == null || value.isEmpty()) {
            throw new InvalidParameterException(label + " is empty.");
        }
    }

    public IUser create() throws InvalidParameterException {
        checkEmpty(userName, "User name");
        checkEmpty(email, "Email address");
        checkEmpty(displayName, "Display name");

        var user = new UserImpl();
        user.setUserName(userName);
        user.setEmail(email);
        user.setDisplayName(displayName);
        return user;
    }

    public UserBuilder setUserName(String userName) {
        this.userName  = userName;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }
}
