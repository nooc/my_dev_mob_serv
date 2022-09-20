package space.nixus.maddoc.rooms;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.Player;
import space.nixus.maddoc.Room;
import space.nixus.maddoc.items.GasMask;
import space.nixus.maddoc.items.LeftFoot;
import space.nixus.maddoc.items.LightSwitch;
import space.nixus.maddoc.items.NarrowWoodenDoor;

public class Basement extends Room {

    public static final String NAME = "basement";
    private static final String[] LINKS = { "entry-hall" };

    public Basement() {
        super();
        inventory.init(new LightSwitch(false), new LeftFoot(), new NarrowWoodenDoor());
    }

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
    public String[] getLinks() {
        return LINKS;
    }

    @Override
    public void describe() {

        Game.fmt("The basement.");
        if(isLit()) {
            Game.fmt("A damp, worn room with no windows.\n" +
                    "There is filthy goo on the floor." +
                    "In the back there is a narrow [%s].",NarrowWoodenDoor.NAME);
        }
        else {
            Game.fmt("It is too dark to see anything but a small\n" +
                    "lantern hold at the base of the stairs to the basement");
        }
        Game.fmt("A stair case leads to the [%s].", EntryHall.NAME);
    }

    @Override
    public boolean movingTo(Player p) {
        var itm = p.getInventory().getItem(GasMask.NAME);
        if(itm!=null && itm.hasFlags("worn") ) {
            return true;
        }
        Game.fmt("A pungent smell of poison and death keeps you from entering.");
        return false;
    }

    @Override
    public boolean locked() {
        return true;
    }
}
