import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Very simple UserRepository for OOP excersize.
 * 
 * @author Ben Bright <nooc@users.noreply.github.com>
 */
public class UserRepository {

    private final ArrayList<User> userList;

    public UserRepository() {
        this.userList = new ArrayList<>();
    }


    public void add(User user) {
        if(find(user.getUsername()) == null) {
            this.userList.add(user);
        }
    }

    public User find(String username) {
        for(User user : userList) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        return Collections.unmodifiableList(userList);
    }

    public User update(User user) {
        var userName = user.getUsername();
        var existing = find(userName);
        if(existing != null) {
            existing.setUsername(userName);
            existing.setPassword(user.getPassword());
            return existing;
        }
        return null;
    }

    public void delete(User user) {
        var existing = find(user.getUsername());
        if(existing != null) {
            userList.remove(existing);
        }
    }
}
