package space.nixus.maddoc.items;

import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Inventory;
import space.nixus.maddoc.Manipulator;
import space.nixus.maddoc.Player;

import java.io.Serializable;

public abstract class BodyPart extends GameItem implements Serializable {

    @Override
    public void use(Player p) {
        Manipulator.cantUnderstand();
    }

    @Override
    public void useOn(Player player, GameItem item) {
        // set flag on table and discard this item
        if(item.getName().equals(RitualTable.NAME)) {
            var name = getName();
            item.setFlag(name);
            player.getInventory().discardItem(name);
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
