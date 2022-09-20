package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Manipulator;
import space.nixus.maddoc.Player;

public class RitualTable extends GameItem {

    public static final String NAME = "ritual-table";

    public String getName() {
        return NAME;
    }

    public void describe(boolean lit, boolean carried) {
        if(lit) {
            Game.fmt("A worn ritual table.\n" +
                    "On the table lies a half rotten, mutilated and stitched up body.");
            if (!hasFlags(SeveredHead.NAME)) {
                Game.fmt("The head is missing.");
            }
            if (!hasFlags(RightHand.NAME)) {
                Game.fmt("The right hand is missing.");
            }
            if (!hasFlags(LeftFoot.NAME)) {
                Game.fmt("The left foot is missing.");
            }
            if (!hasFlags(GlassHeart.NAME)) {
                Game.fmt("There is a cavity in its' chest.");
            }
        }
    }

    public boolean canUse(Player p) {
        return true;
    }

    public void use(Player player) {
        if(player.getInventory().contains("spellbook")) {
            if(hasFlags("righthand", "leftfoot", "head", "glassheart" ))
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
            Game.fmt("You need a spell book in order to perform the ritual.");
        }
    }

    public void useOn(Player player, GameItem item) {
        Manipulator.cantUnderstand();
    }

    public boolean hideInShadow() {
        return true;
    }

    public boolean isStatic() {
        return true;
    }
}
