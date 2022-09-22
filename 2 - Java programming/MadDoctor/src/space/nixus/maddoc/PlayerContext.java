package space.nixus.maddoc;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import space.nixus.maddoc.rooms.*;

import java.io.IOException;
import java.util.HashMap;

/**
 * Player context contains the whole game tree.
 */
public class PlayerContext {
    /** Player inventory. */
    private final Inventory inventory;
    /** Map of all the rooms. */
    private HashMap<String, Room> roomDb;
    /** Current room of player. */
    private Room currentRoom;

    public PlayerContext() {
        buildRooms();
        inventory = new PlayerInventory(this);
    }

    /**
     * Build map of rooms.
     */
    private void buildRooms() {
        roomDb = new HashMap<>();
        addRoom(new EntryHall());
        addRoom(new Basement());
        addRoom(new Kitchen());
        addRoom(new LivingRoom());
        addRoom(new RitualRoom());
        addRoom(new RestRoom());
    }

    /**
     * Add room helper.
     * @param room Room
     */
    private void addRoom(Room room) {
        roomDb.put(room.getName(), room);
    }

    /**
     * Get player inventory.
     * @return Player inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Get current room.
     * @return Room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Move player to room.
     * @param roomId Room id
     * @return success: true or false
     */
    public boolean goTo(String roomId) {
        if (roomDb.containsKey(roomId)) {
            // room exists
            var r = roomDb.get(roomId);
            if (r.movingTo(this)) {
                // "moving to" test succeeded. finalize move.
                currentRoom = roomDb.get(roomId);
                return true;
            }
            return false;
        }
        Manipulator.cantUnderstand();
        return false;
    }

    public void save(JsonWriter writer) throws IOException {
        // write current room
        writer.name("currentRoom").value(currentRoom.getName());
        // write inventory
        writer.name("inventory").beginObject();
        inventory.saveState(writer);
        writer.endObject();
        // write room states
        writer.name("rooms").beginObject();
        for (var room : roomDb.entrySet()) {
            writer.name(room.getKey()).beginObject();
            room.getValue().save(writer);
            writer.endObject();
        }
        writer.endObject();
    }

    public void load(JsonObject cfg) throws Exception {
        if (cfg == null) { // default
            // read current room
            currentRoom = roomDb.get(EntryHall.NAME);
            // load default room states
            for (var room : roomDb.values()) {
                room.load(null);
            }
        } else {
            // get current room
            currentRoom = roomDb.get(cfg.get("currentRoom").getAsString());
            // get inventory
            inventory.loadState(cfg.get("inventory").getAsJsonObject());
            // get room states
            var rooms = cfg.get("rooms").getAsJsonObject();
            for (var room : roomDb.values()) {
                room.load(rooms.get(room.getName()).getAsJsonObject());
            }
        }
    }
}
