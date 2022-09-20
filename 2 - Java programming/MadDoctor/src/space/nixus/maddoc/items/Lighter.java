package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Player;

public class Lighter extends GameItem {

    public static final String NAME = "lighter";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe(boolean lit, boolean carried) {
        Game.fmt("An ordinary [%s].",NAME);
    }

    @Override
    public boolean canUse(Player p) {
        return true;
    }

    @Override
    public void use(Player p) {
        Game.fmt("You try the lighter. Yep, it's working.");
    }

    @Override
    public void useOn(Player player, GameItem item) {
        if(item.hasFlags("can_burn")) {
            item.handleEvent("burn");
        }
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
