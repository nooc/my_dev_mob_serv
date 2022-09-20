package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Player;

import java.io.Serializable;

public class RightHand extends BodyPart implements Serializable {

    public static final String NAME = "right-hand";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe() {
        if(isTouched()) {
            Game.fmt("A severed female hand with broken nails.");
        }
        else {
            Game.fmt("A severed female hand with broken nails.");
        }
    }
}
