package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Manipulator;
import space.nixus.maddoc.Player;

import java.io.Serializable;

public class BasementKey extends GameItem implements Serializable {

    public static final String NAME = "basement-key";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe() {
        if(isTouched()) {
            Game.fmt("The basement door key.");
        }
        else {
            Game.fmt("On the kitchen table lies the basement door key.");
        }
    }

    @Override
    public void use(Player p) {
        Manipulator.cantUnderstand();
    }

    @Override
    public void useOn(Player player, GameItem item) {
        if(item.getName().equals(DoorToBasement.NAME)) {
            item.clearFlag("locked");
            player.getInventory().discardItem(NAME);
            Game.fmt("You unlocked the door.");
        }
        else {
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
