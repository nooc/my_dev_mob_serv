package space.nixus.maddoc;

import java.io.Serializable;
import java.util.HashMap;

public class Player implements Serializable {

    private final HashMap<String,Room> roomDb;
    private Room currentRoom;
    private final Inventory inventory;

    public Player(HashMap<String,Room> roomDb, String startingLocation) {
        currentRoom = roomDb.get(startingLocation);
        this.roomDb = roomDb;
        inventory = new Inventory();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public boolean hasMove(String roomId) {
        return roomDb.containsKey(roomId);
    }

    public boolean goTo(String roomId) {
        if(roomDb.containsKey(roomId)) {
            var r = roomDb.get(roomId);
            if(r.locked()) {
                System.out.println("It's locked!");
            }
            else if (r.movingTo(this)) {
                currentRoom = roomDb.get(roomId);
                return true;
            }
            else {
                System.out.println("I can't do that right now.");
            }
        }
        return false;
    }
}
