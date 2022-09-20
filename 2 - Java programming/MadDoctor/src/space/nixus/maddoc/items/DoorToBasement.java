package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Manipulator;
import space.nixus.maddoc.Player;
import space.nixus.maddoc.rooms.Basement;

import java.io.Serializable;

public class DoorToBasement extends GameItem implements Serializable {

    public static final String NAME = "door-to-basement";

    public DoorToBasement() {
        super();
        setFlag("locked");
    }
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe() {
        Game.fmt("A simple gray door. Behind it there is a staircase to the basement.");
    }

    @Override
    public void use(Player p) {
        if(hasFlags("locked")) {
            Game.fmt("It's locked.");
        }
        else if(p.goTo(Basement.NAME)) {
            Game.fmt("You open the door and take the stairs to the basement.");
        }
    }

    @Override
    public void useOn(Player player, GameItem item) {
        Manipulator.cantUnderstand();
    }

    @Override
    public boolean hideInShadow() {
        return false;
    }

    @Override
    public boolean isStatic() {
        return true;
    }
}
