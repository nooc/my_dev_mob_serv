package space.nixus.maddoc;

import java.util.Random;

public final class Manipulator {

    private static final String[] NOT_UNDERSTOOD = {
            "Huh?", "I don't know about that.", "Can't do that.", "I don't understand.", "Err?",
            "Nope.", "I don't think so.", "Maybe later.", "Hmm..."
    };

    private static Random RND = new Random();

    public static void getItem(PlayerContext p, String name) {
        var inv = p.getCurrentRoom().getInventory();
        inv.moveItem(name, p.getInventory());
    }

    private static GameItem _getItem(PlayerContext player, String name) {
        GameItem item = player.getInventory().getItem(name);
        if (item == null) {
            item = player.getCurrentRoom().getInventory().getItem(name);
        }
        return item;
    }

    public static void useItem(PlayerContext player, String name) {
        GameItem item = _getItem(player, name);
        if (item == null) {
            cantUnderstand();
        } else {
            item.use(player);
        }
    }

    public static void useItemOn(PlayerContext player, String name1, String name2) {
        GameItem item1 = player.getInventory().getItem(name1);
        GameItem item2 = _getItem(player, name2);
        if (item1 == null || item2 == null) {
            cantUnderstand();
        } else {
            item1.useOn(player, item2);
        }
    }

    public static void lookAt(PlayerContext player, String target) {
        Room room = player.getCurrentRoom();
        if (target.equals("room")) {
            room.describe();
        } else if (target.equals("inventory")) {
            player.getInventory().describe();
        } else {
            var itm  = _getItem(player, target);
            if (itm!=null) {
                if(itm.hideInShadow() && !room.isLit()) {
                    Game.fmt("It's too dark.");
                }
                else {
                    itm.describe();
                }
            } else {
                cantUnderstand();
            }
        }
    }

    public static void moveTo(PlayerContext player, String room) {

        if(player.getCurrentRoom().getLinks().contains(room)) {
            player.goTo(room);
        }
        else {
            cantUnderstand();
        }
    }

    public static void cantUnderstand() {
        Game.fmt(NOT_UNDERSTOOD[RND.nextInt(NOT_UNDERSTOOD.length)]);
    }
}
