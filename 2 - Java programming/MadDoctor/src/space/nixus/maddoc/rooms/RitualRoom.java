package space.nixus.maddoc.rooms;

import space.nixus.maddoc.Player;
import space.nixus.maddoc.Room;

public class RitualRoom extends Room {
    private static final String KEY = "ritual-room";

    private static final String[] LINKS = { "basement" };

    public RitualRoom() {
        super(false);
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public String[] getLinks() {
        return new String[0];
    }

    @Override
    public void describe() {

    }

    @Override
    public boolean movingTo(Player p) {
        var inv = p.getInventory();
        return true;
    }

    @Override
    public boolean locked() {
        return false;
    }
}
