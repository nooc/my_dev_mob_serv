package space.nixus.maddoc;

import java.io.Serializable;

public abstract class Room implements Serializable {

    protected final Inventory statInv;
    protected final Inventory dynInv;
    protected boolean hasLight;

    protected Room(boolean lit) {
        statInv = new Inventory();
        dynInv = new Inventory();
        hasLight = lit;
    }

    public Inventory getDynInv() {
        return dynInv;
    }

    public Inventory getStatInv() {
        return statInv;
    }

    public boolean isLit() {
        return hasLight;
    }
    public void toggleLight() {
        hasLight = !hasLight;
    }

    public abstract String getKey();
    public abstract String[] getLinks();
    public abstract void describe();
    public abstract boolean movingTo(Player p);
    public abstract boolean locked();
}
