package space.nixus.maddoc.rooms;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.Player;
import space.nixus.maddoc.Room;
import space.nixus.maddoc.items.LightSwitch;
import space.nixus.maddoc.items.RightHand;
import space.nixus.maddoc.items.WallTorch;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class RitualRoom extends Room implements Serializable {

    public static final String NAME = "ritual-room";
    private static final List<String> LINKS = Arrays.asList( Basement.NAME );

    public RitualRoom() {
        super();
        inventory.init(new RightHand(), new LightSwitch(false));
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
    public boolean movingTo(Player p) {
        return true;
    }
}
