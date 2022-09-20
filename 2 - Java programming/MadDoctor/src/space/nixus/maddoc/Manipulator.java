package space.nixus.maddoc;

import space.nixus.maddoc.rooms.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Random;

public final class Manipulator {

    private static Random RND = new Random();
    private static final String[] NOT_UNDERSTOOD = {
            "Huh?", "I don't know about that.","Can't do that.","I don't understand.","Err?",
            "Nope.", "I don't think so.","Maybe later.","Hmm..."
    };
    private static final Path SAVE_FILE = Path.of("./player.save");

    public static void getItem(Player p, String name) {
        var inv = p.getCurrentRoom().getInventory();
        inv.moveItem(name, p.getInventory());
    }

    private static GameItem _getItem(Player player, String name) {
        GameItem item = player.getInventory().getItem(name);
        if(item==null) {
            item = player.getCurrentRoom().getInventory().getItem(name);
        }
        return item;
    }

    public static void useItem(Player player, String name) {
        GameItem item = _getItem(player, name);
        if(item==null) {
            cantUnderstand();
        }
        else {
            item.use(player);
        }
    }

    public static void useItemOn(Player player, String name1, String name2) {
        GameItem item1 = player.getInventory().getItem(name1);
        GameItem item2 = _getItem(player, name2);
        if(item1==null || item2==null) {
            cantUnderstand();
        }
        else {
            item1.useOn(player,item2);
        }
    }

    public static void lookAt(Player player, String target) {
        Room room = player.getCurrentRoom();
        if(target.equals("room")) {
            room.describe();
        }
        else if(target.equals("inventory")) {
            player.getInventory().describe();
        }
        else {
            var pInv = player.getInventory();
            var rInv = room.getInventory();
            if(pInv.contains(target)) {
                pInv.getItem(target).describe(room.isLit(), true);
            }
            else if(rInv.contains(target)) {
                rInv.getItem(target).describe(room.isLit(), false);
            }
            else {
                cantUnderstand();
            }
        }
    }

    public static void moveTo(Player player, String room) {
        player.goTo(room);
    }

    public static Player loadGame() {
        Player player;
        try {
            var s = new ObjectInputStream(new FileInputStream(SAVE_FILE.toFile()));
            player = (Player)s.readObject();
            Game.fmt("Continuing game...\n\n");
        }
        catch (Exception ex)
        {
            Game.fmt("Starting new game...\nType 'help' for more information.\n\n");
            HashMap<String,Room> rooms = new HashMap<>();
            var start = addRoom(rooms, new EntryHall());
            addRoom(rooms, new EntryHall());
            addRoom(rooms, new Basement());
            addRoom(rooms, new Kitchen());
            addRoom(rooms, new LivingRoom());
            addRoom(rooms, new RitualRoom());
            player = new Player(rooms, start);
        }
        return player;
    }

    private static String addRoom(HashMap<String,Room> l, Room room) {
        var name = room.getName();
        l.put(name, room);
        return name;
    }

    public static void saveGame(Player player) {
        try {
            var s = new ObjectOutputStream(new FileOutputStream(SAVE_FILE.toFile()));
            s.writeObject(player);
            Game.fmt("Game saved.");
        }
        catch (Exception ex) {
            Game.fmt("Failed to save game!");
        }
    }

    public static void cantUnderstand() {
        Game.fmt(NOT_UNDERSTOOD[RND.nextInt(NOT_UNDERSTOOD.length)]);
    }
}
