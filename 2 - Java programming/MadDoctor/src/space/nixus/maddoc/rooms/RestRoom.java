package space.nixus.maddoc.rooms;

import com.google.gson.JsonObject;
import space.nixus.maddoc.Game;
import space.nixus.maddoc.PlayerContext;
import space.nixus.maddoc.Room;
import space.nixus.maddoc.items.LightSwitch;
import space.nixus.maddoc.items.SpellBook;

import java.util.List;

public class RestRoom extends Room {

    /** Name/id */
    public static final String NAME = "rest-room";
    /** List of room connections. */
    public static final List<String> LINKS = List.of(EntryHall.NAME);

    @Override
    public boolean isLit() {
        return inventory.getItem(LightSwitch.NAME).hasFlags("on");
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public List<String> getLinks() {
        return LINKS;
    }

    @Override
    public void describe() {
        Game.fmt("The rest room.");
        if (isLit()) {
            Game.fmt("Your average public rest room.\n" +
                    "Not so sanitary...");
        } else {
            Game.fmt("You need light.");
        }
        Game.fmt("You can go back to the [%s].", EntryHall.NAME);
        inventory.describe();
    }

    @Override
    public boolean movingTo(PlayerContext p) {
        return true;
    }

    public void load(JsonObject cfg) throws Exception {
        if (cfg == null) {
            inventory.init(new LightSwitch(), new SpellBook());
        } else super.load(cfg);
    }
}
