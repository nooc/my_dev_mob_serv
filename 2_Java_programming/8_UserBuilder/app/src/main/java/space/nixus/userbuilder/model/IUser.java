package space.nixus.userbuilder.model;

public interface IUser {
    String getUserName();
    void setUserName(String name);

    String getEmail();
    void setEmail(String name);

    String getDisplayName();
    void setDisplayName(String name);
}
