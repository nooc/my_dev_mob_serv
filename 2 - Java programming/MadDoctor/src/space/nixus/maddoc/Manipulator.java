package space.nixus.maddoc;

import java.util.Random;

/**
 * Helper class used by Game.
 */
public final class Manipulator {

    /**
     * Random responses for commands not understood.
     */
    private static final String[] NOT_UNDERSTOOD = {
            "Huh?", "I don't know about that.", "Can't do that.", "I don't understand.", "Err?",
            "Nope.", "I don't think so.", "Maybe later.", "Hmm..."
    };

    /** Randomizer. */
    private static final Random RND = new Random();

    /**
     * Picks up item command.
     * @param ctx Context
     * @param name Item id
     */
    public static void getItem(PlayerContext ctx, String name) {
        var inv = ctx.getCurrentRoom().getInventory();
        inv.moveItem(name, ctx.getInventory());
    }

    /**
     * Try to get item from available inventories.
     * @param ctx PlayerContext
     * @param name Item id
     * @return GameItem or null
     */
    private static GameItem _getItem(PlayerContext ctx, String name) {
        GameItem item = ctx.getInventory().getItem(name);
        if (item == null) {
            item = ctx.getCurrentRoom().getInventory().getItem(name);
        }
        return item;
    }

    /**
     * Use item command.
     * @param ctx PlayerContext
     * @param name Item id
     */
    public static void useItem(PlayerContext ctx, String name) {
        GameItem item = _getItem(ctx, name);
        if (item == null) {
            cantUnderstand();
        } else {
            item.use(ctx);
        }
    }

    /**
     * Use item on another item command.
     * @param ctx
     * @param name1 Item id 1
     * @param name2 Item id 2
     */
    public static void useItemOn(PlayerContext ctx, String name1, String name2) {
        GameItem item1 = ctx.getInventory().getItem(name1);
        GameItem item2 = _getItem(ctx, name2);
        if (item1 == null || item2 == null) {
            cantUnderstand();
        } else {
            item1.useOn(ctx, item2);
        }
    }

    /**
     * Look at command.
     * If target is "room", look at room.
     * If target is "inventory, look in player inventory.
     * Else look at target item.
     * @param ctx PlayerContext
     * @param target Look at target
     */
    public static void lookAt(PlayerContext ctx, String target) {
        Room room = ctx.getCurrentRoom();
        if (target.equals("room")) {
            room.describe();
        } else if (target.equals("inventory")) {
            ctx.getInventory().describe();
        } else {
            var itm = _getItem(ctx, target);
            if (itm != null) {
                // Cant see if its dark and item is hidden in darkness.
                if (itm.hideInShadow() && !room.isLit()) {
                    Game.fmt("It's too dark.");
                } else {
                    itm.describe();
                }
            } else {
                cantUnderstand();
            }
        }
    }

    /**
     * Move to room command.
     * @param ctx PlayerContext
     * @param room Room id
     */
    public static void moveTo(PlayerContext ctx, String room) {

        if (ctx.getCurrentRoom().getLinks().contains(room)) {
            ctx.goTo(room);
        } else {
            cantUnderstand();
        }
    }

    /**
     * Print random text from NOT_UNDERSTOOD.
     */
    public static void cantUnderstand() {
        Game.fmt(NOT_UNDERSTOOD[RND.nextInt(NOT_UNDERSTOOD.length)]);
    }
}
