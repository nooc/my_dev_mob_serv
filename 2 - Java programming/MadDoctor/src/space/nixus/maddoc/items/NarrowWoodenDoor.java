package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Manipulator;
import space.nixus.maddoc.Player;
import space.nixus.maddoc.rooms.RitualRoom;

import java.io.Serializable;

public class NarrowWoodenDoor extends GameItem implements Serializable {

    public static final String NAME = "wooden-door";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe() {
        Game.fmt("In the back there is a narrow wooden door.");
    }

    @Override
    public void use(Player p) {
        if(p.goTo(RitualRoom.NAME)) {
            Game.fmt("You go through the narrow door.");
        }
    }

    @Override
    public void useOn(Player player, GameItem item) {
        Manipulator.cantUnderstand();
    }

    @Override
    public boolean hideInShadow() {
        return true;
    }

    @Override
    public boolean isStatic() {
        return false;
    }
}
