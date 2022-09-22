package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Manipulator;
import space.nixus.maddoc.PlayerContext;

public class GasMask extends GameItem {

    public static final String NAME = "gas-mask";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe() {
        if (hasFlags("touched")) {
            Game.fmt("A [%s].", NAME);
        } else {
            Game.fmt("A [%s] is tucked under the sofa.", NAME);
        }
    }

    /**
     * Use gas mask to wear it or remove it.
     * @param ctx Context
     */
    @Override
    public void use(PlayerContext ctx) {
        if (hasFlags("worn")) {
            clearFlag("worn");
            Game.fmt("You remove the mask.");
        } else {
            setFlag("worn");
            Game.fmt("You put on the mask.");
        }
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
