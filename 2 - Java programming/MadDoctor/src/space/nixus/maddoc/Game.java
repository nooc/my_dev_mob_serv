package space.nixus.maddoc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * MadDoctor
 */
public class Game {
    private static final Path SAVE_FILE = Path.of("./maddoc.save");
    private static Gson gson = null;

    public static void main(String[] args) {

        var builder = new GsonBuilder();
        builder.setPrettyPrinting();
        gson = builder.create();
        PlayerContext player = new PlayerContext();
        Scanner scan = new Scanner(System.in);

        try {
            player.load(getConfig());
        }
        catch (Exception ex) {
            fmt("Error loading game: " + ex.getMessage());
            Game.exit();
        }

        fmt("MadDoctor 1.0\nCopyright (c) 2022 - Ben Bright");
        while (true) {
            fmt0("\n[%s] > ", player.getCurrentRoom().getName());
            String cmdLine = scan.nextLine().trim().toLowerCase();
            String[] cmds = cmdLine.replaceAll("\\s+", " ").split(" ");
            var cmd = cmds[0];

            if (cmd.equals("help")) {
                fmt("GAME INTERFACE:");
                fmt("quit - Quit the game.");
                fmt("save - Save the game.");
                fmt("look - Look around in the current room.");
                fmt("lookat <target> - Look at the target.");
                fmt("inv - Show inventory.");
                fmt("get <item> - Pick up item from the current room.");
                fmt("use <item> - Use item in the current room or in your inventory.");
                fmt("use <item> on <target> - Use source on target.");
                fmt("put <item> on <target> - Put source on target.");
                fmt("drop <item> - Drop item in current room.");
                fmt("goto <target> - Move to target.");

            } else if (cmd.equals("get") && cmds.length == 2) {
                Manipulator.getItem(player, cmds[1]);
            } else if (cmd.equals("use") && (cmds.length == 2 || (cmds.length == 4 && cmds[2].equals("on")))) {
                if (cmds.length == 2) {
                    Manipulator.useItem(player, cmds[1]);
                } else {
                    Manipulator.useItemOn(player, cmds[1], cmds[3]);
                }
            } else if (cmd.equals("look")) {
                Manipulator.lookAt(player, "room");
            } else if (cmd.equals("inv")) {
                Manipulator.lookAt(player, "inventory");
            } else if (cmd.equals("lookat") && cmds.length == 2) {
                Manipulator.lookAt(player, cmds[1]);
            } else if (cmd.equals("goto") && cmds.length == 2) {
                Manipulator.moveTo(player, cmds[1]);
            } else if (cmd.equals("save")) {
                try(var writer = getWriter()) {
                    writer.beginObject();
                    player.save(writer);
                    writer.endObject();
                    fmt("Game saved.");
                }
                catch (Exception ex) {
                    fmt("Error when trying to save: " + ex.getMessage());
                }
            } else if (cmd.equals("quit")) {
                System.out.println("BYE");
                break;
            } else {
                Manipulator.cantUnderstand();
            }
        }
    }

    private static JsonWriter getWriter() throws IOException {
        return gson.newJsonWriter(new FileWriter(SAVE_FILE.toFile()));
    }
    private static JsonObject getConfig() {
        try(var reader = new FileReader(SAVE_FILE.toFile())) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        }
        catch (Exception ex) {
            return null;
        }
    }

    public static void fmt(String format, Object... params) {

        System.out.format(format, params);
        System.out.println();
    }

    public static void fmt0(String format, Object... params) {

        System.out.format(format, params);
    }

    public static void exit() {
        System.exit(0);
    }
}
