package space.nixus.maddoc.items;

import com.google.gson.JsonObject;
import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.PlayerContext;

public class SpellBook extends GameItem {

    public static final String NAME = "spell-book";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe() {
        if (hasFlags("touched")) {
            Game.fmt("A [%s] for various rituals.", NAME);
        } else {
            Game.fmt("Behind the toilet seat lies a [%s].", NAME);
        }
    }

    @Override
    public void use(PlayerContext p) {
        Game.fmt("You wouldn't know where to start.");
    }

    /**
     * Using the spell book on the ritual table will end the game if the
     * ritual table is complete and the player is not wearing the gas mask.
     * @param ctx Context
     * @param item Target
     */
    @Override
    public void useOn(PlayerContext ctx, GameItem item) {
        var mask = ctx.getInventory().getItem(GasMask.NAME);
        if(mask!=null && mask.hasFlags("worn")) {
            Game.fmt("You can not read the text when wearing the gas mask.");
        }
        else if (item.getName().equals(RitualTable.NAME)) {
            if (!item.hasFlags(RightHand.NAME, LeftFoot.NAME, SeveredHead.NAME, GlassHeart.NAME)) {
                Game.fmt("Something is missing from the ritual.");
            } else {
                Game.fmt(Game.ENDING);
                Game.exit();
            }
        } else {
            Game.fmt("Nothing happens.");
        }
    }

    @Override
    public void loadState(JsonObject cfg) {
        super.loadState(cfg);
        if (cfg == null) {
            setFlag("can_burn"); // add default flag
        }
    }

    /**
     * Burn this spell book. This will effectively prevent the player from
     * completing the game.
     * @param player Context
     * @param id Event id
     */
    @Override
    public void handleEvent(PlayerContext player, String id) {
        if (id.equals("burn")) {
            player.getInventory().discardItem(NAME);
            player.getCurrentRoom().getInventory().discardItem(NAME);
            Game.fmt("The spell book burns to ashes.");
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
