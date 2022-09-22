package space.nixus.maddoc.rooms;

import com.google.gson.JsonObject;
import space.nixus.maddoc.Game;
import space.nixus.maddoc.PlayerContext;
import space.nixus.maddoc.Room;
import space.nixus.maddoc.items.BasementDoor;
import space.nixus.maddoc.items.Lighter;

import java.util.Arrays;
import java.util.List;

public final class EntryHall extends Room {

    /** Name/id */
    public static final String NAME = "entry-hall";
    /** List of room connections. */
    private static final List<String> LINKS = Arrays.asList(LivingRoom.NAME, RestRoom.NAME);

    @Override
    public boolean isLit() {
        return true;
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
        Game.fmt("The entrance hall.\n" +
                        "The walls are worn and there is filth everywhere.\n" +
                        "From here you can continue to the [%s] or go to the [%s]",
                LivingRoom.NAME, RestRoom.NAME);
        inventory.describe();
    }

    @Override
    public boolean movingTo(PlayerContext p) {
        return true;
    }

    public void load(JsonObject cfg) throws Exception {
        if (cfg == null) {
            inventory.init(new Lighter(), new BasementDoor());
        } else super.load(cfg);
    }
}
