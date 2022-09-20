package space.nixus.maddoc.rooms;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.Player;
import space.nixus.maddoc.Room;
import space.nixus.maddoc.items.DoorToBasement;
import space.nixus.maddoc.items.Lighter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public final class EntryHall extends Room implements Serializable {

    public static final String NAME = "entry-hall";
    private static final List<String> LINKS = Arrays.asList( LivingRoom.NAME, RestRoom.NAME );

    public EntryHall() {
        super();
        inventory.init(new Lighter(), new DoorToBasement());
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
    public boolean movingTo(Player p) {
        return true;
    }
}
