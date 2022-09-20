package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;

import java.io.Serializable;

public class LeftFoot extends BodyPart implements Serializable {

    public static final String NAME = "left-foot";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe() {
        if(isTouched()) {
            Game.fmt("A severed and bruised left foot of a female.");
        }
        else {
            Game.fmt("In one of the corners lies a severed left foot.");
        }
    }
}
