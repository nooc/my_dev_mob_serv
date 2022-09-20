package space.nixus.maddoc.items;

import space.nixus.maddoc.Game;
import space.nixus.maddoc.GameItem;
import space.nixus.maddoc.Manipulator;
import space.nixus.maddoc.Player;

import java.io.Serializable;

public class WallTorch extends GameItem implements Serializable {

    public static final String NAME = "wall-torch";

    public WallTorch() {
        super();
        setFlag("can_burn");
    }

    @Override
    public String getName() {
        return "wall-torch";
    }

    @Override
    public void describe() {
        if(hasFlags("lit")) {
            Game.fmt("A lit wall torch giving off yellowish light.");
        }
        else {
            Game.fmt("A barely visible and unlit wall torch.");
        }
    }

    @Override
    public void handleEvent(String id) {
        if(id.equals("burn"))
        {
            if(hasFlags("lit")) {
                Game.fmt("The torch is already lit.");
            }
            else {
                Game.fmt("You light the torch.");
                setFlag("lit");
            }
        }
    }

    @Override
    public void use(Player p) {
        Manipulator.cantUnderstand();
    }

    @Override
    public void useOn(Player player, GameItem item) {
        Manipulator.cantUnderstand();
    }

    @Override
    public boolean hideInShadow() {
        return false;
    }

    @Override
    public boolean isStatic() {
        return true;
    }
}
