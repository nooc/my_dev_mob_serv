package space.nixus.maddoc.items;

import com.google.gson.JsonObject;
import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Manipulator;
import space.nixus.maddoc.PlayerContext;

/**
 * @author Ben Bright <nooc@users.noreply.github.com>
 */
public class WallTorch extends GameItem {

    public static final String NAME = "wall-torch";

    @Override
    public String getName() {
        return "wall-torch";
    }

    @Override
    public void describe() {
        if (hasFlags(LIT)) {
            Game.fmt("A lit [%s] giving off yellowish light.", NAME);
        } else {
            Game.fmt("A barely visible and unlit [%s].", NAME);
        }
    }

    /**
     * Handle burn event by lighting this torch.
     *
     * @param ctx Context
     * @param id  Event id
     */
    @Override
    public void handleEvent(PlayerContext ctx, String id) {
        if (id.equals(BURN)) {
            if (hasFlags(LIT)) {
                Game.fmt("The torch is already lit.");
            } else {
                Game.fmt("You light the torch.");
                setFlag(LIT);
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
            setFlag(CAN_BURN); // add default flag
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
