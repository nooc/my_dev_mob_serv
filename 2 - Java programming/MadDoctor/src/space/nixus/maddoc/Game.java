package space.nixus.maddoc;

import space.nixus.maddoc.rooms.*;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final String[] NOT_UNDERSTOOD = {
            "Huh?", "I don't know about that.","Can't do that.","I don't understand.","Err?"
    };
    private static final Path SAVE_FILE = Path.of("./player.save");

    public static void main(String[] args) {

        Random rnd = new Random();
        Player player;

        System.out.println("MadDoctor 1.0\nCopyright (c) 2022 - Ben Bright");

        try {
            var s = new ObjectInputStream (new FileInputStream(SAVE_FILE.toFile()));
            player = (Player)s.readObject();
            System.out.println("Continuing game...\n\n");
        }
        catch (Exception ex)
        {
            System.out.println("Starting new game...\nType 'help' for more information.\n\n");

            HashMap<String,Room> rooms = new HashMap<>();
            var start = addRoom(rooms, new EntryHall());
            addRoom(rooms, new Basement());
            addRoom(rooms, new Kitchen());
            addRoom(rooms, new RitualRoom());
            player = new Player(rooms, start);
        }
        Scanner scan = new Scanner(System.in);

        player.getCurrentRoom().describe();

        while(true) {
            System.out.print(">>> ");
            var cmdLine = scan.nextLine().trim().toLowerCase().split("\\s+");
            var cmd = cmdLine[0];

            if(cmd.equals("help")) {
                System.out.println("GAME INTERFACE");
                System.out.println("quit - Quit the game.");
                System.out.println("save - Save the game.");
                System.out.println("look [<target>] - Look at the current room or at the optional target.");
                System.out.println("get <item> - Pick up item from the current room.");
                System.out.println("use <item> - Use item in the current room or in your inventory.");
                System.out.println("use <item> on <target> - Use source on target.");
                System.out.println("put <item> on <target> - Put source on target.");
                System.out.println("drop <item> - Drop item in current room.");
                System.out.println("move <target> - Move to target.");

            }
            else if(cmd.equals("use") && (cmdLine.length==2 || (cmdLine.length==4 && cmdLine[2].equals("on")))) {
                if(cmdLine.length==2) {
                    System.out.println("using "+cmdLine[1]);
                }
                else {
                    System.out.println("using "+cmdLine[1]+" on "+cmdLine[3]);
                }
            }
            else if(cmd.equals("look") && cmdLine.length < 3) {
                if(cmdLine.length==1) {
                    player.getCurrentRoom().describe();
                }
                else {
                    // look at something
                }
            }
            else if(cmd.equals("move") && cmdLine.length==2) {
                var r = player.getCurrentRoom();
                if(player.hasMove(cmdLine[1])) {
                    player.goTo(cmdLine[1]);
                }
                else {
                    System.out.println("Can't go there.");
                }
            }
            else if (cmd.equals("save")) {
                try {
                    var s = new ObjectOutputStream (new FileOutputStream(SAVE_FILE.toFile()));
                    s.writeObject(player);
                    System.out.println("Game saved.");
                }
                catch (Exception ex) {
                    System.out.println("Failed to save game!");
                }
            }
            else if(cmd.equals("quit")) {
                System.out.println("BYE");
                break;
            }
            else {
                System.out.println(NOT_UNDERSTOOD[rnd.nextInt(NOT_UNDERSTOOD.length)]);
            }
        }
    }

    private static String addRoom(HashMap<String,Room> l, Room r) {
        l.put(r.getKey(), r);
        return r.getKey();
    }
}
