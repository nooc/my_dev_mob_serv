package space.nixus.maddoc;

/**
 * @author Ben Bright <nooc@users.noreply.github.com>
 */
public class PlayerInventory extends Inventory {
    /**
     * PlayerContext of inventory.
     */
    private final PlayerContext ctx;

    public PlayerInventory(PlayerContext ctx) {
        this.ctx = ctx;
    }

    public void describe() {
        Game.fmt("---INVENTORY---");
        var lit = ctx.getCurrentRoom().isLit();
        for (var e : content.entrySet()) {
            Game.fmt0("[%s]: ", e.getKey());
            e.getValue().describe();
        }
        Game.fmt("---------------");
    }
}
