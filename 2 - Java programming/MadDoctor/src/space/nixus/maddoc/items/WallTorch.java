package space.nixus.maddoc.items;

import com.google.gson.JsonObject;
import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Manipulator;
import space.nixus.maddoc.PlayerContext;

public class WallTorch extends GameItem {

    public static final String NAME = "wall-torch";

    @Override
    public String getName() {
        return "wall-torch";
    }

    @Override
    public void describe() {
        if (hasFlags("lit")) {
            Game.fmt("A lit [%s] giving off yellowish light.", NAME);
        } else {
            Game.fmt("A barely visible and unlit [%s].", NAME);
        }
    }

    /**
     * Handle burn event by lighting this torch.
     * @param player Context
     * @param id Event id
     */
    @Override
    public void handleEvent(PlayerContext player, String id) {
        if (id.equals("burn")) {
            if (hasFlags("lit")) {
                Game.fmt("The torch is already lit.");
            } else {
                Game.fmt("You light the torch.");
                setFlag("lit");
            }
        }
    }

    @Override
    public void use(PlayerContext p) {
        Manipulator.cantUnderstand();
    }

    @Override
    public void useOn(PlayerContext player, GameItem item) {
        Manipulator.cantUnderstand();
    }

    @Override
    public void loadState(JsonObject cfg) {
        super.loadState(cfg);
        if (cfg == null) {
            setFlag("can_burn"); // add default flag
        }
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
