package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;

/**
 * @author Ben Bright <nooc@users.noreply.github.com>
 */
public class GlassHeart extends BodyPart {

    public static final String NAME = "glass-heart";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe() {
        if (hasFlags(TOUCHED)) {
            Game.fmt("A red [%s] artifact.", NAME);
        } else {
            Game.fmt("There is a red [%s] lying on the floor.", NAME);
        }
    }
}
