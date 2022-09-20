package space.nixus.maddoc.rooms;

import space.nixus.maddoc.Player;
import space.nixus.maddoc.Room;

public class RitualRoom extends Room {
    public static final String NAME = "ritual-room";

    private static final String[] LINKS = { "basement" };

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String[] getLinks() {
        return new String[0];
    }

    @Override
    public void describe() {

    }

    @Override
    public boolean isLit() {
        var itm = getInventory().getItem("wall-torch");
        return itm!=null && itm.hasFlags("lit");
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
