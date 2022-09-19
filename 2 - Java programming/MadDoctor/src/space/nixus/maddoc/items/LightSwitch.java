package space.nixus.maddoc.items;

import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Player;
import space.nixus.maddoc.Room;

public class LightSwitch implements GameItem {

    private Room room;
    private static final String NAME = "switch";

    public LightSwitch(Room r) {
        room = r;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription(boolean lit) {
        return "A light switch on the wall.";
    }

    @Override
    public boolean canUse(Player p) {
        return true;
    }

    @Override
    public boolean use(Player p) {
        room.toggleLight();
        System.out.println("You flip the light switch.");
        return true;
    }

    @Override
    public boolean hideInShadow() {
        return false;
    }
}
