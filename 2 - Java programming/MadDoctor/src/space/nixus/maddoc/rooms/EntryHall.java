package space.nixus.maddoc.rooms;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.Player;
import space.nixus.maddoc.Room;
import space.nixus.maddoc.items.Lighter;

import java.io.Serializable;

public final class EntryHall extends Room implements Serializable {

    public static final String NAME = "entry-hall";
    private static final String[] LINKS = { "kitchen", "basement" };

    public EntryHall() {
        super();
        inventory.init(new Lighter());
    }

    @Override
    public boolean isLit() {
        return true;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String[] getLinks() {
        return LINKS;
    }

    @Override
    public void describe() {
        Game.fmt("The entrance hall.\n" +
                "The walls are worn and there is filth everywhere.\n" +
                "From here you can continue to the [%s].\n" +
                "There is a small staircase leading to the [%s].",
                LivingRoom.NAME, Basement.NAME);
        inventory.describe();
    }

    @Override
    public boolean movingTo(Player p) {
        return true;
    }

    @Override
    public boolean locked() {
        return false;
    }
}
