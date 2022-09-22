package space.nixus.maddoc.items;

import com.google.gson.JsonObject;
import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Manipulator;
import space.nixus.maddoc.PlayerContext;
import space.nixus.maddoc.rooms.Basement;

import java.io.Serializable;

public class DoorToBasement extends GameItem {

    public static final String NAME = "door-to-basement";

    public DoorToBasement() {
        super();
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
    public void use(PlayerContext p) {
        if(hasFlags("locked")) {
            Game.fmt("It's locked.");
        }
        else if(p.goTo(Basement.NAME)) {
            Game.fmt("You open the door and take the stairs to the basement.");
        }
        touch();
    }

    @Override
    public void useOn(PlayerContext player, GameItem item) {
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

    @Override
    public void load(JsonObject cfg) {
        super.load(cfg);
        if(cfg == null) { setFlag("locked"); }
    }
}
