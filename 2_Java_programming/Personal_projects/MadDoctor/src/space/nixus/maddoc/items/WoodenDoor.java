package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Manipulator;
import space.nixus.maddoc.PlayerContext;
import space.nixus.maddoc.rooms.RitualRoom;

public class WoodenDoor extends GameItem {

    public static final String NAME = "wooden-door";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe() {
        Game.fmt("In the back there is a narrow [%s].", NAME);
    }

    @Override
    public void use(PlayerContext p) {
        if (p.goTo(RitualRoom.NAME)) {
            Game.fmt("You go through the narrow door.");
        }
        setFlag("touched");
    }

    @Override
    public void useOn(PlayerContext player, GameItem item) {
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
