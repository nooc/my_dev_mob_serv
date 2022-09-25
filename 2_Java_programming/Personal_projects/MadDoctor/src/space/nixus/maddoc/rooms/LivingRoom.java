package space.nixus.maddoc.rooms;

import com.google.gson.JsonObject;
import space.nixus.maddoc.Game;
import space.nixus.maddoc.PlayerContext;
import space.nixus.maddoc.Room;
import space.nixus.maddoc.items.GasMask;
import space.nixus.maddoc.items.GlassHeart;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ben Bright <nooc@users.noreply.github.com>
 */
public class LivingRoom extends Room {

    /**
     * Name/id
     */
    public static final String NAME = "living-room";
    /**
     * List of room connections.
     */
    private static final List<String> LINKS = Arrays.asList(EntryHall.NAME, Kitchen.NAME);

    @Override
    public boolean isLit() {
        return true;
    }

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
        Game.fmt("The living room is a sad sight indeed. There is litter all\n" +
                "over the place. On one side a half torn sofa sits on a rug\n" +
                "hosting various dead mice. The smell is unbearable.");
        Game.fmt("You can go to the [%s] or the [%s].", EntryHall.NAME, Kitchen.NAME);
        inventory.describe();
    }

    @Override
    public boolean movingTo(PlayerContext p) {
        return true;
    }

    @Override
    public void loadState(JsonObject cfg) throws Exception {
        super.loadState(cfg);
        if (cfg == null) {
            inventory.init(new GlassHeart(), new GasMask());
        }
    }
}
