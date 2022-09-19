package space.nixus.maddoc.rooms;

import space.nixus.maddoc.Player;
import space.nixus.maddoc.Room;

import java.io.Serializable;

public final class EntryHall extends Room implements Serializable {

    private static final String KEY = "entry-hall";
    private static final String[] LINKS = { "kitchen", "basement" };

    public EntryHall() {
        super(true);
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public String[] getLinks() {
        return LINKS;
    }

    @Override
    public void describe() {
        System.out.println("A small entry hall from where you can go to the [kitchen]" +
                " or down to the [basement].");
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
