package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;

/**
 * @author Ben Bright <nooc@users.noreply.github.com>
 */
public class LeftFoot extends BodyPart {

    public static final String NAME = "left-foot";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe() {
        if (hasFlags(TOUCHED)) {
            Game.fmt("A severed and bruised [%s] of a female.", NAME);
        } else {
            Game.fmt("In one of the corners lies a severed [%s].", NAME);
        }
    }
}
