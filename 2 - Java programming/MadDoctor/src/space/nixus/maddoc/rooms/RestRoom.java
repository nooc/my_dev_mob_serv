package space.nixus.maddoc.rooms;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.Player;
import space.nixus.maddoc.Room;
import space.nixus.maddoc.items.LightSwitch;
import space.nixus.maddoc.items.SpellBook;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class RestRoom extends Room implements Serializable {

    public static final String NAME = "rest-room";
    public static final List<String> LINKS = Arrays.asList(EntryHall.NAME);

    public RestRoom() {
        super();
        inventory.init(new LightSwitch(false), new SpellBook());
    }

    @Override
    public boolean isLit() {
        return inventory.getItem(LightSwitch.NAME).hasFlags("on");
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
        Game.fmt("The rest room.");
        if(isLit()) {
            Game.fmt("Your average public rest room.\n" +
                    "Not so sanitary...");
        }
        else {
            Game.fmt("You need light.");
        }
        Game.fmt("You can go back to the [%s].", EntryHall.NAME);
        inventory.describe();
    }

    @Override
    public boolean movingTo(Player p) {
        return true;
    }
}
