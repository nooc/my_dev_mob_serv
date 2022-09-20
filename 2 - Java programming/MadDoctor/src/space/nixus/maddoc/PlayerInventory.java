package space.nixus.maddoc;

import java.io.Serializable;

public class PlayerInventory extends Inventory implements Serializable {

    public static final String TYPE = "player";
    private Player player;

    public PlayerInventory(Player player) {
        this.player = player;
    }

    public void describe() {
        Game.fmt("---INVENTORY---");
        var lit = player.getCurrentRoom().isLit();
        for (var e : content.entrySet()) {
            Game.fmt0("[%s]: ", e.getKey());
            e.getValue().describe();
        }
        Game.fmt("---------------");
    }

    public String invType() {
        return TYPE;
    }
}
