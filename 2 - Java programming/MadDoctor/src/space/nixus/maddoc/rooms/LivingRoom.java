package space.nixus.maddoc.rooms;

import space.nixus.maddoc.Player;
import space.nixus.maddoc.Room;
import space.nixus.maddoc.items.GlassHeart;

public class LivingRoom extends Room {

    public static final String NAME = "living-room";
    private static final String[] LINKS = { "entry-hall", "kitchen" };

    public LivingRoom() {
        super();
        inventory.init(new GlassHeart());
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
