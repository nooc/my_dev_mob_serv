package space.nixus.maddoc.rooms;

import com.google.gson.JsonObject;
import space.nixus.maddoc.Game;
import space.nixus.maddoc.PlayerContext;
import space.nixus.maddoc.Room;
import space.nixus.maddoc.items.LightSwitch;
import space.nixus.maddoc.items.RightHand;
import space.nixus.maddoc.items.RitualTable;
import space.nixus.maddoc.items.WallTorch;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class RitualRoom extends Room {

    public static final String NAME = "ritual-room";
    private static final List<String> LINKS = Arrays.asList( Basement.NAME );

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public List<String> getLinks() {
        return LINKS;
    }

    @Override
    public void describe() {
        if(isLit()) {
            Game.fmt("The room is very small. A table is set\n" +
                    "in the center of the room.");
        }
        else {
            Game.fmt("It is dark and damp.");
        }
        inventory.describe();
    }

    @Override
    public boolean isLit() {
        var itm = getInventory().getItem(WallTorch.NAME);
        return itm!=null && itm.hasFlags("lit");
    }

    @Override
    public boolean movingTo(PlayerContext p) {
        return true;
    }

    public void load(JsonObject cfg) throws Exception {
        if(cfg==null) {
            inventory.init(new RitualTable(), new WallTorch(), new RightHand());
        }
        else super.load(cfg);
    }
}
