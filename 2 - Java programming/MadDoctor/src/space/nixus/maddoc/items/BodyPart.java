package space.nixus.maddoc.items;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Manipulator;
import space.nixus.maddoc.PlayerContext;

import java.io.Serializable;

public abstract class BodyPart extends GameItem {

    @Override
    public void use(PlayerContext p) {
        Manipulator.cantUnderstand();
    }

    @Override
    public void useOn(PlayerContext player, GameItem item) {
        // set flag on table and discard this item
        if(item.getName().equals(RitualTable.NAME)) {
            item.setFlag(getName());
            player.getInventory().discardItem(getName());
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
