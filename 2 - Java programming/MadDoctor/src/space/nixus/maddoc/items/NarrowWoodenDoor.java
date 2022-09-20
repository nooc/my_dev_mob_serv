package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Player;
import space.nixus.maddoc.rooms.RitualRoom;

public class NarrowWoodenDoor extends GameItem {
    public static final String NAME = "wooden-door";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe(boolean lit, boolean carried) {

    }

    @Override
    public boolean canUse(Player p) {
        return true;
    }

    @Override
    public void use(Player p) {
        if(p.goTo(RitualRoom.NAME)) {
            Game.fmt("You go through the narrow door.");
        }
    }

    @Override
    public void useOn(Player player, GameItem item) {

    }

    @Override
    public boolean hideInShadow() {
        return false;
    }

    @Override
    public boolean isStatic() {
        return false;
    }
}
