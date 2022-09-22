package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;

public class SeveredHead extends BodyPart {

    public static final String NAME = "severed-head";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe() {
        if (hasFlags("touched")) {
            Game.fmt("An eyeless [%s] of an old man.", NAME);
        } else {
            Game.fmt("Floating in the kitchen sink you see an eyeless [%s] of an old man.", NAME);
        }
    }
}
