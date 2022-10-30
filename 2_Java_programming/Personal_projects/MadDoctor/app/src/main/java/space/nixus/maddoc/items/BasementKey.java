package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Manipulator;
import space.nixus.maddoc.PlayerContext;

/**
 * @author Ben Bright <nooc@users.noreply.github.com>
 */
public class BasementKey extends GameItem {

    public static final String NAME = "basement-key";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe() {
        if (hasFlags(TOUCHED)) {
            Game.fmt("[%s] for the basement door.", NAME);
        } else {
            Game.fmt("On the kitchen table lies the [%s].", NAME);
        }
    }

    @Override
    public void use(PlayerContext p) {
        Manipulator.cantUnderstand();
    }

    /**
     * This key can be used on DoorToBasement.
     * Once used, unlocks door and discards key.
     *
     * @param player Context
     * @param item   Target
     */
    @Override
    public void useOn(PlayerContext player, GameItem item) {
        if (item.getName().equals(BasementDoor.NAME)) {
            item.clearFlag(LOCKED);
            player.getInventory().discardItem(NAME);
            Game.fmt("You unlocked the door.");
        } else {
            Manipulator.cantUnderstand();
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
