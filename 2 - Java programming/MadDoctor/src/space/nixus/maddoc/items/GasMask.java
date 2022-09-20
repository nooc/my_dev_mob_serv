package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Manipulator;
import space.nixus.maddoc.Player;

public class GasMask extends GameItem {

    public static final String NAME = "gas-mask";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe(boolean lit, boolean carried) {
        Game.fmt("A gas mask.");
    }

    @Override
    public boolean canUse(Player p) {
        return true;
    }

    @Override
    public void use(Player p) {
        if(hasFlags("worn")) {
            Game.fmt("You are already wearing the mask.");
        }
        else {
            setFlag("worn");
            Game.fmt("You put on the mask.");
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
