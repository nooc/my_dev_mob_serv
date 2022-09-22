package space.nixus.maddoc.items;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import space.nixus.maddoc.*;

import java.io.Serializable;

public class LightSwitch extends GameItem {

    public LightSwitch() {
        super();
    }
    public static final String NAME = "light-switch";

    public String getName() {
        return NAME;
    }

    public void describe() {
        Game.fmt("There is a light switch on the wall.");
    }

    public void use(PlayerContext p) {
        if(hasFlags("on")) {
            clearFlag("on");
        }
        else {
            setFlag("on");
        }
        Game.fmt("You flip the light switch.");
        touch();
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
