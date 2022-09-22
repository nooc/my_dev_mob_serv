package space.nixus.maddoc.items;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.PlayerContext;

import java.io.Serializable;

public class Lighter extends GameItem {

    public static final String NAME = "lighter";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe() {
        if(isTouched()) {
            Game.fmt("An ordinary lighter.");
        }
        else {
            Game.fmt("On a stool lies what looks to be a lighter.");
        }
    }

    @Override
    public void use(PlayerContext p) {
        Game.fmt("You try the lighter. Yep, it's working.");
    }

    @Override
    public void useOn(PlayerContext player, GameItem item) {
        if (item.hasFlags("can-burn")) {
            item.handleEvent(player,"burn");
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
