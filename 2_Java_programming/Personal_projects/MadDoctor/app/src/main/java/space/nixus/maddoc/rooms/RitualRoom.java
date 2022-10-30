package space.nixus.maddoc.rooms;

import com.google.gson.JsonObject;
import space.nixus.maddoc.Game;
import space.nixus.maddoc.PlayerContext;
import space.nixus.maddoc.Room;
import space.nixus.maddoc.items.RightHand;
import space.nixus.maddoc.items.RitualTable;
import space.nixus.maddoc.items.WallTorch;

import java.util.List;

/**
 * @author Ben Bright <nooc@users.noreply.github.com>
 */
public class RitualRoom extends Room {

    /**
     * Name/id
     */
    public static final String NAME = "ritual-room";
    /**
     * List of room connections.
     */
    private static final List<String> LINKS = List.of(Basement.NAME);

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
        if (isLit()) {
            Game.fmt("The room is very small. A table is set\n" +
                    "in the center of the room.");
        } else {
            Game.fmt("It is dark and damp.");
        }
        inventory.describe();
    }

    @Override
    public boolean isLit() {
        return getInventory().getItem(WallTorch.NAME).hasFlags("lit");
    }

    @Override
    public boolean movingTo(PlayerContext p) {
        return true;
    }

    @Override
    public void loadState(JsonObject cfg) throws Exception {
        super.loadState(cfg);
        if (cfg == null) {
            inventory.init(new RitualTable(), new WallTorch(), new RightHand());
        }
    }
}
