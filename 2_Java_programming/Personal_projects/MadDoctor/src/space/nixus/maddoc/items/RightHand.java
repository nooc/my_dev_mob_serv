package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;

public class RightHand extends BodyPart {

    public static final String NAME = "right-hand";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe() {
        if (hasFlags(TOUCHED)) {
            Game.fmt("A severed female [%s] with broken nails.", NAME);
        } else {
            Game.fmt("On the floor lies a [%s].", NAME);
        }
    }
}
