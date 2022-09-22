package space.nixus.maddoc;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public abstract class GameItem {

    private final Set<String> flags;
    private boolean touched;

    protected GameItem() {
        touched = false;
        flags = new HashSet<>();
    }

    public final boolean hasFlags(String... flg) {
        for (var f : flg) {
            if (!flags.contains(f)) {
                return false;
            }
        }
        return true;
    }
    public final void setFlag(String flg) {
        flags.add(flg);
    }
    public final void clearFlag(String flg) {
        flags.remove(flg);
    }
    public final void touch() { touched = true;}
    public final boolean isTouched() { return touched; }

    public void handleEvent(PlayerContext player, String id) { }

    public void save(JsonWriter writer) throws IOException {
        if(!flags.isEmpty()) {
            writer.name("flags").beginArray();
            for (var f : flags) { writer.value(f); }
            writer.endArray();
        }
        writer.name("touched").value(touched);
    }

    public void load(JsonObject cfg) {
        if(cfg != null) {
            touched = cfg.get("touched").getAsBoolean();
            if (cfg.has("flags")) {
                var arr = cfg.get("flags").getAsJsonArray();
                var i = arr.iterator();
                while (i.hasNext()) {
                    flags.add(i.next().getAsString());
                }
            }
        }
    }
    public abstract String getName();

    public abstract void describe();

    public abstract void use(PlayerContext p);

    public abstract void useOn(PlayerContext player, GameItem item);

    public abstract boolean hideInShadow();

    public abstract boolean isStatic();
}
