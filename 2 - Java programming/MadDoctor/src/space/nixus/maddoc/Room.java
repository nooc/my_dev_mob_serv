package space.nixus.maddoc;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.List;

public abstract class Room {
    protected final Inventory inventory;

    protected Room() {
        inventory = new RoomInventory(this);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void save(JsonWriter writer) throws IOException {
        writer.name("inventory").beginObject();
        inventory.save(writer);
        writer.endObject();
    }

    public void load(JsonObject cfg) throws Exception {
        if(cfg != null) {
            inventory.load(cfg.get("inventory").getAsJsonObject());
        }
    }

    public abstract boolean isLit();

    public abstract String getName();

    public abstract List<String> getLinks();

    public abstract void describe();

    public abstract boolean movingTo(PlayerContext p);
}
