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
 * MadDoctor game class.
 * 
 * @author Ben Bright <nooc@users.noreply.github.com>
 */
public class Game {

    public static final String BEGINNING = """
            Something has gone awry. You know you have been living in this
            house for a while, experimenting with occult rituals, but alas,
            you seem to have lost your memory. All you can remember is that
            you should complete the experiment...""";
    public static final String ENDING = """
            You begin chanting the spell as described in the spell book.
            A cold breeze sweeps across the room and the light is
            suppressed by materialized darkness emanating from the body.
            You head a horrid, unnatural roar and seconds there after the
            clattering of something moving in the darkness. Before you
            can react, the thing leaps up next to you and whispers in
            your ear, "Wake up..."

            You wake up in your bed, drenched in sweat.

            THE END""";
    /**
     * Save file location.
     */
    private static final Path SAVE_FILE = Path.of("./maddoc.save");
    /**
     * Google json.
     */
    private static Gson gson = null;

    /**
     * Program entry
     */
    public static void main(String[] args) {

        // create gson
        var builder = new GsonBuilder();
        builder.setPrettyPrinting();
        gson = builder.create();
        // create player
        PlayerContext player = new PlayerContext();
        // crete input
        Scanner scan = new Scanner(System.in);

        fmt("MadDoctor 1.0\nCopyright (c) 2022 - Ben Bright");

        // try loading game
        try {
            var cfg = getConfig();
            player.load(cfg);
            if (cfg == null) {
                fmt(BEGINNING);
            }
        } catch (Exception ex) {
            fmt("Error loading game: " + ex.getMessage());
            Game.exit();
        }

        // game loop
        while (true) {
            // read and split command line
            fmt0("\n[%s] > ", player.getCurrentRoom().getName());
            String cmdLine = scan.nextLine().trim().toLowerCase();
            String[] cmds = cmdLine.replaceAll("\\s+", " ").split(" ");

            // perform command
            var cmd = cmds[0];
            if (cmd.equals("help")) {
                fmt("""
                        GAME INTERFACE:
                        quit - Quit the game.
                        save - Save the game.
                        look - Look around in the current room.
                        lookat <target> - Look at the target.
                        inv - Show inventory.
                        get <item> - Pick up item from the current room.
                        use <item> - Use item in the current room or in your inventory.
                        use <item> on <target> - Use source on target.
                        put <item> on <target> - Put source on target.
                        drop <item> - Drop item in current room.
                        goto <target> - Move to target.""");

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
                // write game state to json file
                try (var writer = getWriter()) {
                    writer.beginObject();
                    player.save(writer);
                    writer.endObject();
                    fmt("Game saved.");
                } catch (Exception ex) {
                    fmt("Error when trying to save: " + ex.getMessage());
                }
            } else if (cmd.equals("quit")) {
                // break loop
                fmt("BYE");
                break;
            } else {
                Manipulator.cantUnderstand();
            }
        }
    }

    /**
     * Get json writer.
     *
     * @return JsonWriter
     * @throws IOException
     */
    private static JsonWriter getWriter() throws IOException {
        return gson.newJsonWriter(new FileWriter(SAVE_FILE.toFile()));
    }

    /**
     * Get JsonObject from SAVE_FILE.
     *
     * @return JsonObject
     */
    private static JsonObject getConfig() {
        try (var reader = new FileReader(SAVE_FILE.toFile())) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * String output formatter with newline.
     * All game output is going through fmt or fmt0.
     *
     * @param format Format string
     * @param params Format parameters
     */
    public static void fmt(String format, Object... params) {

        System.out.format(format, params);
        System.out.println();
    }

    /**
     * String output formatter without newline.
     * All game output is going through fmt or fmt0.
     *
     * @param format Format string
     * @param params Format parameters
     */
    public static void fmt0(String format, Object... params) {

        System.out.format(format, params);
    }

    /**
     * Exit game.
     */
    public static void exit() {
        System.exit(0);
    }
}
