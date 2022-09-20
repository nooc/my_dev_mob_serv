package space.nixus.maddoc;

import java.util.Scanner;

/**
 * MadDoctor
 *
 *
 */
public class Game {
    public static void main(String[] args) {

        Player player;
        fmt("MadDoctor 1.0\nCopyright (c) 2022 - Ben Bright");
        player = Manipulator.loadGame();
        Scanner scan = new Scanner(System.in);

        while(true) {
            fmt0("[%s]\n>>> ", player.getCurrentRoom().getName());
            String cmdLine = scan.nextLine().trim().toLowerCase();
            String[] cmds = cmdLine.replaceAll("\\s+", " ").split(" ");
            var cmd = cmds[0];

            if(cmd.equals("help")) {
                fmt("GAME INTERFACE:");
                fmt("quit - Quit the game.");
                fmt("save - Save the game.");
                fmt("lookat room|inventory|<target> - Look around in the current room or at the target.");
                fmt("get <item> - Pick up item from the current room.");
                fmt("use <item> - Use item in the current room or in your inventory.");
                fmt("use <item> on <target> - Use source on target.");
                fmt("put <item> on <target> - Put source on target.");
                fmt("drop <item> - Drop item in current room.");
                fmt("goto <target> - Move to target.");

            }
            else if(cmd.equals("get") && cmds.length == 2) {
                Manipulator.getItem(player, cmds[1]);
            }
            else if(cmd.equals("use") && (cmds.length==2 || (cmds.length==4 && cmds[2].equals("on")))) {
                if(cmds.length==2) {
                    Manipulator.useItem(player, cmds[1]);
                }
                else {
                    Manipulator.useItemOn(player, cmds[1], cmds[3]);
                }
            }
            else if(cmd.equals("lookat") && cmds.length == 2) {
                Manipulator.lookAt(player, cmds[1]);
            }
            else if(cmd.equals("goto") && cmds.length==2) {
                Manipulator.moveTo(player, cmds[1]);
            }
            else if (cmd.equals("save")) {
                Manipulator.saveGame(player);
            }
            else if(cmd.equals("quit")) {
                System.out.println("BYE");
                break;
            }
            else {
                Manipulator.cantUnderstand();
            }
        }
    }
    public static void fmt(String format, Object...params) {

        System.out.format(format, params);
        System.out.println();
    }
    public static void fmt0(String format, Object...params) {

        System.out.format(format, params);
    }

    public static void exit() {
        System.exit(0);
    }
}
