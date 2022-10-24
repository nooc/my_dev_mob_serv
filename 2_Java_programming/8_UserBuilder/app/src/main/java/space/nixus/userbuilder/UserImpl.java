package space.nixus.userbuilder;

import space.nixus.userbuilder.model.IUser;

public class UserImpl implements IUser {

    private String userName;
    private String email;
    private String displayName;


    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName  = userName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    @Override
    public String toString() {
        return String.format("%s <%s>", displayName, email);
    }
}
