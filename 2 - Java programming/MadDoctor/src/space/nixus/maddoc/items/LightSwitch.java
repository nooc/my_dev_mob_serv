package space.nixus.maddoc.items;

import space.nixus.maddoc.*;

import java.io.Serializable;

public class LightSwitch extends GameItem implements Serializable {

    public LightSwitch(boolean state) {
        super();
        if(state) {
            setFlag("on");
        }
    }
    public static final String NAME = "light-switch";

    public String getName() {
        return NAME;
    }

    public void describe() {
        Game.fmt("There is a light switch on the wall.");
    }

    public void use(Player p) {
        if(hasFlags("on")) {
            clearFlag("on");
        }
        else {
            setFlag("on");
        }
        Game.fmt("You flip the light switch.");
    }

    public void useOn(Player player, GameItem item) {
        Manipulator.cantUnderstand();
    }

    public boolean hideInShadow() {
        return false;
    }

    public boolean isStatic() {
        return true;
    }
}
