package space.nixus.maddoc;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.HashMap;

/**
 * Base class for inventories.
 */
public abstract class Inventory implements GameStateIO {
    protected static final String CONTENT_KEY = "content";

    /**
     * Map of inventory items.
     */
    protected final HashMap<String, GameItem> content;

    protected Inventory() {
        content = new HashMap<>();
    }

    /**
     * Initialize inventory with items.
     *
     * @param items
     */
    public void init(GameItem... items) {
        for (var item : items) {
            content.put(item.getName(), item);
            item.loadState(null); // default
        }
    }

    /**
     * Describe inventory to console.
     */
    public abstract void describe();

    /**
     * Get item from inventory.
     *
     * @param name Item id
     * @return GameItem
     */
    public GameItem getItem(String name) {
        return content.get(name);
    }

    /**
     * Move item from this inventory to target inventory.
     *
     * @param name   Item id
     * @param target inventory
     * @return success: true or false
     */
    public boolean moveItem(String name, Inventory target) {
        var item = getItem(name);
        if (item == null || item.isStatic()) {
            // cant move static or null
            Manipulator.cantUnderstand();
            return false;
        } else {
            content.remove(name);
            target.content.put(name, item);
            item.setFlag(GameItem.TOUCHED);
            // pickup or drop based on target type
            if (target instanceof PlayerInventory) {
                Game.fmt("You pick up %s.", name);
            } else {
                Game.fmt("You drop %s.", name);

            }
        }
        return true;
    }

    /**
     * Discard item from inventory.
     *
     * @param name Item id
     */
    public void discardItem(String name) {
        var item = getItem(name);
        // can't discard static or null
        if (item != null && !item.isStatic()) {
            content.remove(name);
        }
    }

    public void saveState(JsonWriter writer) throws IOException {
        // write items to content property
        writer.name(CONTENT_KEY).beginObject();
        for (var ent : content.entrySet()) {
            var obj = ent.getValue();
            writer.name(obj.getClass().getName()).beginObject();
            ent.getValue().saveState(writer); // write item state
            writer.endObject();
        }
        writer.endObject();
    }

    public void loadState(JsonObject cfg) throws Exception {
        if (cfg != null) {
            // read items from content property
            var contentCfg = cfg.get(CONTENT_KEY).getAsJsonObject();
            for (var item : contentCfg.entrySet()) {
                // get item config
                var itemCfg = item.getValue().getAsJsonObject();
                // instantiate item class
                var cl = Class.forName(item.getKey());
                GameItem gi = (GameItem) cl.getConstructor((Class<?>) null).newInstance();
                gi.loadState(itemCfg); // load item state
                content.put(gi.getName(), gi);
            }
        }
    }
}
