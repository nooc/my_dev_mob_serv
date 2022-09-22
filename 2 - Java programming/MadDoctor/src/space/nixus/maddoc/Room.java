package space.nixus.maddoc;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.List;

/**
 * Base class for rooms.
 */
public abstract class Room implements GameStateIO {
    protected final Inventory inventory;

    protected Room() {
        inventory = new RoomInventory(this);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void saveState(JsonWriter writer) throws IOException {
        writer.name("inventory").beginObject();
        inventory.saveState(writer);
        writer.endObject();
    }

    public void loadState(JsonObject cfg) throws Exception {
        if (cfg != null) {
            inventory.loadState(cfg.get("inventory").getAsJsonObject());
        }
    }

    /**
     * Test if room is lit.
     *
     * @return
     */
    public abstract boolean isLit();

    /**
     * Get room id/name.
     *
     * @return
     */
    public abstract String getName();

    /**
     * Get possible goto locations from here.
     *
     * @return List of location ids.
     */
    public abstract List<String> getLinks();

    /**
     * Describe room to console.
     */
    public abstract void describe();

    /**
     * Called before moving to a room.
     *
     * @param ctx
     * @return False to cancel move, else true.
     */
    public abstract boolean movingTo(PlayerContext ctx);
}
