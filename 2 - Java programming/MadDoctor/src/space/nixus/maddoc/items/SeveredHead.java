package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;

import java.io.Serializable;

public class SeveredHead extends BodyPart {

    public static final String NAME = "severed-head";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe() {
        if(isTouched()) {
            Game.fmt("An eyeless severed head of an old man.");
        }
        else {
            Game.fmt("Floating in the kitchen sink you see an eyeless severed head of an old man.");
        }
    }
}
