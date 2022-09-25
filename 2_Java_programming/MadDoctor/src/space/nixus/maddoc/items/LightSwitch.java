package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Manipulator;
import space.nixus.maddoc.PlayerContext;

public class LightSwitch extends GameItem {

    public static final String NAME = "light-switch";

    public LightSwitch() {
        super();
    }

    public String getName() {
        return NAME;
    }

    public void describe() {
        Game.fmt("There is a [%s] on the wall.", NAME);
    }

    public void use(PlayerContext p) {
        if (hasFlags(ON)) {
            clearFlag(ON);
        } else {
            setFlag(ON);
        }
        Game.fmt("You flip the light switch.");
        setFlag(TOUCHED);
    }

    public void useOn(PlayerContext player, GameItem item) {
        Manipulator.cantUnderstand();
    }

    public boolean hideInShadow() {
        return false;
    }

    public boolean isStatic() {
        return true;
    }
}
