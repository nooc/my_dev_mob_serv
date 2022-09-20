package space.nixus.maddoc;

import space.nixus.maddoc.Inventory;

public class PlayerInventory extends Inventory {

    public static final String TYPE = "player";
    private Player player;

    public PlayerInventory(Player player) {
        this.player = player;
    }

    public void describe() {
        Game.fmt("---INVENTORY---");
        var lit = player.getCurrentRoom().isLit();
        for (var e: content.entrySet()) {
            Game.fmt0("%s: ",e.getKey());
            e.getValue().describe(lit, true);
        }
        Game.fmt("---------------");
    }

    public String invType() { return TYPE; }
}
