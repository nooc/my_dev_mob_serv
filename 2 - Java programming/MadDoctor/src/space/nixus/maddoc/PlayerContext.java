package space.nixus.maddoc;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import space.nixus.maddoc.rooms.*;

import java.io.IOException;
import java.util.HashMap;

public class PlayerContext {

    private HashMap<String, Room> roomDb;
    private final Inventory inventory;
    private Room currentRoom;

    public PlayerContext() {
        buildRooms();
        inventory = new PlayerInventory(this);
    }

    private void buildRooms() {
        roomDb = new HashMap<>();
        addRoom(new EntryHall());
        addRoom(new Basement());
        addRoom(new Kitchen());
        addRoom(new LivingRoom());
        addRoom(new RitualRoom());
        addRoom(new RestRoom());
    }
    private void addRoom(Room room) {
        roomDb.put(room.getName(), room);
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

    public void save(JsonWriter writer) throws IOException {

        writer.name("currentRoom").value(currentRoom.getName());

        writer.name("inventory").beginObject();
        inventory.save(writer);
        writer.endObject();

        writer.name("rooms").beginObject();
        for (var room : roomDb.entrySet()) {
            writer.name(room.getKey()).beginObject();
            room.getValue().save(writer);
            writer.endObject();
        }
        writer.endObject();
    }

    public void load(JsonObject cfg) throws Exception {
        if(cfg==null) {
            currentRoom = roomDb.get(EntryHall.NAME);
            for (var room : roomDb.values()) {
                room.load(null);
            }
        }
        else {
            currentRoom = roomDb.get(cfg.get("currentRoom").getAsString());
            inventory.load(cfg.get("inventory").getAsJsonObject());
            var rooms = cfg.get("rooms").getAsJsonObject();
            for (var room : roomDb.values()) {
                room.load(rooms.get(room.getName()).getAsJsonObject());
            }
        }
    }
}
