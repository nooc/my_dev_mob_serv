package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Manipulator;
import space.nixus.maddoc.Player;

public class BasementKey extends GameItem {
    public static final String NAME = "basement-key";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe(boolean lit, boolean carried) {
        Game.fmt("The basement door key.");
    }

    @Override
    public boolean canUse(Player p) {
        return true;
    }

    @Override
    public void use(Player p) {
        Manipulator.cantUnderstand();
    }

    @Override
    public void useOn(Player player, GameItem item) {

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
