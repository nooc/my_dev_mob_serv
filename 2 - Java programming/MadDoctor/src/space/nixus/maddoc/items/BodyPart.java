package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Manipulator;
import space.nixus.maddoc.PlayerContext;

/**
 * Base class for body parts.
 */
public abstract class BodyPart extends GameItem {
    @Override
    public void use(PlayerContext ctx) {
        Manipulator.cantUnderstand();
    }

    /**
     * Use body part on ritual table.
     * Sets flag on RitualTable and discards this item.
     * @param ctx PlayerContext
     * @param item RitualTable
     */
    @Override
    public void useOn(PlayerContext ctx, GameItem item) {
        // set flag on table and discard this item
        if (item.getName().equals(RitualTable.NAME)) {
            item.setFlag(getName());
            ctx.getInventory().discardItem(getName());
            Game.fmt("You place the %s on the %s.",
                    getName(), RitualTable.NAME);
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
