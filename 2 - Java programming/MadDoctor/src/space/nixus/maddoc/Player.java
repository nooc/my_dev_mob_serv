package space.nixus.maddoc;

import java.io.Serializable;
import java.util.HashMap;

public class Player implements Serializable {

    private final HashMap<String, Room> roomDb;
    private final Inventory inventory;
    private Room currentRoom;

    public Player(HashMap<String, Room> roomDb, String startingLocation) {
        currentRoom = roomDb.get(startingLocation);
        this.roomDb = roomDb;
        inventory = new PlayerInventory(this);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public boolean goTo(String roomId) {
        if (roomDb.containsKey(roomId)) {
            var r = roomDb.get(roomId);
            if (r.movingTo(this)) {
                currentRoom = roomDb.get(roomId);
                return true;
            }
            return false;
        }
        Manipulator.cantUnderstand();
        return false;
    }
}
