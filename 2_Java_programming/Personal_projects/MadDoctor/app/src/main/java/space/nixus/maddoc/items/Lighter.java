package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.PlayerContext;

/**
 * @author Ben Bright <nooc@users.noreply.github.com>
 */
public class Lighter extends GameItem {

    public static final String NAME = "lighter";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe() {
        if (hasFlags(TOUCHED)) {
            Game.fmt("An ordinary [%s].", NAME);
        } else {
            Game.fmt("On a stool lies what looks to be a [%s].", NAME);
        }
    }

    @Override
    public void use(PlayerContext p) {
        Game.fmt("You try the lighter. Yep, it's working.");
    }

    @Override
    public void useOn(PlayerContext player, GameItem item) {
        if (item.hasFlags(CAN_BURN)) {
            item.handleEvent(player, BURN);
        }
    }

    @Override
    public boolean hideInShadow() {
        return true;
    }

    @Override
    public boolean isStatic() {
        return false;
    }
}
