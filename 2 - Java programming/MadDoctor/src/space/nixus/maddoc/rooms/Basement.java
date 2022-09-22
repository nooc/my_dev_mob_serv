package space.nixus.maddoc.rooms;

import com.google.gson.JsonObject;
import space.nixus.maddoc.Game;
import space.nixus.maddoc.PlayerContext;
import space.nixus.maddoc.Room;
import space.nixus.maddoc.items.GasMask;
import space.nixus.maddoc.items.LeftFoot;
import space.nixus.maddoc.items.LightSwitch;
import space.nixus.maddoc.items.NarrowWoodenDoor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Basement extends Room {

    public static final String NAME = "basement";
    private static final List<String> LINKS = Arrays.asList( EntryHall.NAME);

    @Override
    public boolean isLit() {
        var item = getInventory().getItem(LightSwitch.NAME);
        return item != null && item.hasFlags("on");
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

        Game.fmt("The basement.");
        if(isLit()) {
            Game.fmt("A damp, worn room with no windows.\n" +
                    "Some kind of sticky substance is covering the floor.");
        }
        else {
            Game.fmt("It is too dark to see much.");
        }
        Game.fmt("You can go back to the [%s].", EntryHall.NAME);
        inventory.describe();
    }

    @Override
    public boolean movingTo(PlayerContext p) {
        var itm = p.getInventory().getItem(GasMask.NAME);
        if(itm!=null && itm.hasFlags("worn") ) {
            return true;
        }
        Game.fmt("A pungent smell of poison and death keeps you from entering.");
        return false;
    }

    public void load(JsonObject cfg) throws Exception {
        if(cfg==null) {
            inventory.init(new LightSwitch(), new LeftFoot(), new NarrowWoodenDoor());
        }
        else super.load(cfg);
    }
}
