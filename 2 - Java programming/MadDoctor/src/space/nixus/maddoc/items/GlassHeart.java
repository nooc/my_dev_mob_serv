package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;

import java.io.Serializable;


public class GlassHeart extends BodyPart implements Serializable {

    public static final String NAME = "glass-heart";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe() {
        if(isTouched()) {
            Game.fmt("A red glass artifact shaped like a heart.");
        }
        else {
            Game.fmt("A red heart shaped object lies on the floor.");
        }
    }
}
