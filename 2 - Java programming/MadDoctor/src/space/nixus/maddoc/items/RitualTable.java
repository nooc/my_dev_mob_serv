package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Manipulator;
import space.nixus.maddoc.PlayerContext;

import java.io.Serializable;

public class RitualTable extends GameItem {

    public static final String NAME = "ritual-table";

    public String getName() {
        return NAME;
    }

    public void describe() {
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

    public void use(PlayerContext player) {
        Manipulator.cantUnderstand();
    }

    public void useOn(PlayerContext player, GameItem item) {
        Manipulator.cantUnderstand();
    }

    public boolean hideInShadow() {
        return true;
    }

    public boolean isStatic() {
        return true;
    }
}
