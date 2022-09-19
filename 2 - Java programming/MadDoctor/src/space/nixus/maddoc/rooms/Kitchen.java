package space.nixus.maddoc.rooms;

import space.nixus.maddoc.Player;
import space.nixus.maddoc.Room;

public class Kitchen extends Room {

    private static final String KEY = "kitchen";
    private static final String[] LINKS = { "main-hall" };

    public Kitchen() {
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
