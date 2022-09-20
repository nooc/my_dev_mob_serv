package space.nixus.maddoc;

import java.io.Serializable;

public abstract class Room implements Serializable {
    protected final Inventory inventory;

    protected Room() {
        inventory = new RoomInventory(this);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public abstract boolean isLit();

    public abstract String getName();
    public abstract String[] getLinks();
    public abstract void describe();
    public abstract boolean movingTo(Player p);
    public abstract boolean locked();
}
