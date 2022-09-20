package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Player;

import java.io.Serializable;

public class SpellBook extends GameItem implements Serializable {

    public static final String NAME = "spell-book";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void describe() {
        if(isTouched()) {
            Game.fmt("A book of spells for various rituals.");
        }
        else {
            Game.fmt("Behind the toilet seat lies a book.");
        }
    }

    @Override
    public void use(Player p) {
        Game.fmt("You wouldn't know where to start.");
    }

    @Override
    public void useOn(Player player, GameItem item) {
        if(item.getName().equals(RitualTable.NAME)) {
            if(! item.hasFlags(RightHand.NAME, LeftFoot.NAME, SeveredHead.NAME, GlassHeart.NAME ))
            {
                Game.fmt("Something is missing from the ritual.");
            }
            else {
                Game.fmt("You begin chanting the spell as described in the spell book.\n" +
                        "A cold breeze sweeps across the room and the light is\n" +
                        "suppressed by materialized darkness emanating from the body.\n" +
                        "You head a horrid, unnatural roar and seconds there after the clattering\n" +
                        "of something moving in the darkness. Before you can react, the thing\n" +
                        "leaps up nex to you and whispers in your ear: Wake up...\n" +
                        "You wake up in your bed, drenched in sweat.\n\n" +
                        "THE END");
                Game.exit();
            }
        }
        else {
            Game.fmt("Nothing happens.");
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
