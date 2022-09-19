package space.nixus.maddoc.rooms;

import space.nixus.maddoc.Player;
import space.nixus.maddoc.Room;

public class Basement extends Room {

    private static final String KEY = "basement";
    private static final String[] LINKS = { "kitchen", "basement" };

    public Basement() {
        super(false);
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public String[] getLinks() {
        return LINKS;
    }

    @Override
    public void describe() {
        System.out.println("The basement.");
        if(hasLight) {
            System.out.println("A damp, worn room with no windows.\n" +
                    "There is filthy goo on the floor." +
                    "There is a narrow [wooden-door] in the back.");
        }
        else {
            System.out.println("It is too dark to see anything but a small\n" +
                    "lantern hold at the base of the stairs to the basement");
        }
    }

    @Override
    public boolean movingTo(Player p) {
        var inv = p.getInventory();
        if(inv.contains("gas-mask")) {
            return true;
        }
        System.out.println("A pungent smell of poison and death keeps you from entering.");
        return false;
    }

    @Override
    public boolean locked() {
        return true;
    }
}
