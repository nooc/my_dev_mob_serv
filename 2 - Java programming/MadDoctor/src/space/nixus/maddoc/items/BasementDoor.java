package space.nixus.maddoc.items;

import com.google.gson.JsonObject;
import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Manipulator;
import space.nixus.maddoc.PlayerContext;
import space.nixus.maddoc.rooms.Basement;

public class BasementDoor extends GameItem {

    public static final String NAME = "basement-door";

    public BasementDoor() {
        super();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe() {

        Game.fmt("You see a simple gray [%s] leading to the basement.", NAME);
    }

    /**
     * Using this door will take the player to the basement
     * if unlocked.
     * @param ctx Context
     */
    @Override
    public void use(PlayerContext ctx) {
        if (hasFlags("locked")) {
            Game.fmt("It's locked.");
        } else if (ctx.goTo(Basement.NAME)) {
            Game.fmt("You open the door and take the stairs to the basement.");
        }
        setFlag("touched");
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
    public void loadState(JsonObject cfg) {
        super.loadState(cfg);
        if (cfg == null) {
            setFlag("locked");
        }
    }
}
