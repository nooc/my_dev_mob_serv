package space.nixus.maddoc;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public abstract class Inventory {
    protected HashMap<String, GameItem> content;

    protected Inventory() {
        content = new HashMap<>();
    }

    public void init(GameItem... items) {
        for (var item : items) {
            content.put(item.getName(), item);
        }
    }

    public abstract void describe();

    public abstract String invType();

    public GameItem getItem(String name) {
        return content.get(name);
    }

    public boolean moveItem(String name, Inventory target) {
        var item = getItem(name);
        if (item == null || item.isStatic()) {
            Manipulator.cantUnderstand();
            return false;
        } else {
            content.remove(name);
            target.content.put(name, item);
            item.touch();
            if (target.invType().equals(PlayerInventory.TYPE)) {
                Game.fmt("You pick up %s.", name);
            } else {
                Game.fmt("You drop %s.", name);

            }
        }
        return true;
    }

    public void discardItem(String name) {
        var item = getItem(name);
        if (item != null && !item.isStatic()) {
            content.remove(name);
        }
    }

    public void save(JsonWriter writer) throws IOException {
        writer.name("content").beginObject();
        for (var ent : content.entrySet()) {
            var obj = ent.getValue();
            writer.name(obj.getClass().getName()).beginObject();
            ent.getValue().save(writer);
            writer.endObject();
        }
        writer.endObject();
    }
    public void load(JsonObject cfg) throws Exception {
        if(cfg != null) {
            var contentCfg = cfg.get("content").getAsJsonObject();
            for(var item : contentCfg.entrySet()) {
                var itemCfg = item.getValue().getAsJsonObject();
                var cl = Class.forName(item.getKey());
                GameItem gi = (GameItem)cl.getConstructor(null).newInstance();
                gi.load(itemCfg);
                content.put(gi.getName(), gi);
            }
        }
    }
}
