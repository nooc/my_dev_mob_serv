package space.nixus.maddoc;

public class PlayerInventory extends Inventory {

    public static final String TYPE = "player";
    private PlayerContext player;

    public PlayerInventory(PlayerContext player) {
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
