package space.nixus.maddoc;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Base class for game items.
 */
public abstract class GameItem implements GameStateIO{

    /** Item flags. */
    private final Set<String> flags;

    protected GameItem() {
        flags = new HashSet<>();
    }

    /**
     * Test if this item has all the given flags.
     * @param flgs
     * @return True if all flags exist, else false.
     */
    public final boolean hasFlags(String... flgs) {
        for (var f : flgs) {
            if (!flags.contains(f)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Set a flag.
     * @param flg Flag
     */
    public final void setFlag(String flg) {
        flags.add(flg);
    }

    /**
     * Clear a flag.
     * @param flg Flag
     */
    public final void clearFlag(String flg) {
        flags.remove(flg);
    }

    /**
     * Handle an event.
     * @param player Context
     * @param id Event id
     */
    public void handleEvent(PlayerContext player, String id) { }

    public void saveState(JsonWriter writer) throws IOException {
        // write flags
        if (!flags.isEmpty()) {
            writer.name("flags").beginArray();
            for (var f : flags) {
                writer.value(f);
            }
            writer.endArray();
        }
    }

    public void loadState(JsonObject cfg) {
        // read flags
        if (cfg != null) {
            if (cfg.has("flags")) {
                var arr = cfg.get("flags").getAsJsonArray();
                var i = arr.iterator();
                while (i.hasNext()) {
                    flags.add(i.next().getAsString());
                }
            }
        }
    }

    /**
     * Get item name.
     * @return Name
     */
    public abstract String getName();

    /**
     * Describe item to console.
     */
    public abstract void describe();

    /**
     * Use item.
     * @param ctx Context
     */
    public abstract void use(PlayerContext ctx);

    /**
     * Use item on target.
     * @param ctx Context
     * @param item Target
     */
    public abstract void useOn(PlayerContext ctx, GameItem item);

    /**
     * Test if item is hidden in the dark.
     * @return true or false
     */
    public abstract boolean hideInShadow();

    /**
     * Test if item is static (can't be transferred).
     * @return true or false
     */
    public abstract boolean isStatic();
}
