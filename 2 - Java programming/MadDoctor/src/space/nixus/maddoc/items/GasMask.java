package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Manipulator;
import space.nixus.maddoc.PlayerContext;

import java.io.Serializable;

public class GasMask extends GameItem {

    public static final String NAME = "gas-mask";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe() {
        if(isTouched()) {
            Game.fmt("A gas mask.");
        }
        else {
            Game.fmt("A gas mask is tucked under the sofa.");
        }
    }

    @Override
    public void use(PlayerContext p) {
        if(hasFlags("worn")) {
            Game.fmt("You are already wearing the mask.");
        }
        else {
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
