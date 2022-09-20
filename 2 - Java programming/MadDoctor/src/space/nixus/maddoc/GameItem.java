package space.nixus.maddoc;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class GameItem implements Serializable {

    private final Set<String> flags;
    private boolean untouched;

    protected GameItem() {
        untouched = false;
        flags = new HashSet<>();
    }

    public final boolean hasFlags(String... flg) {
        for (var f : flg) {
            if (!flags.contains(f)) {
                return false;
            }
        }
        return true;
    }
    public final void setFlag(String flg) {
        flags.add(flg);
    }
    public final void clearFlag(String flg) {
        flags.remove(flg);
    }
    public final void touch() { untouched = true;}
    public final boolean isTouched() { return untouched; }

    public void handleEvent(String id) { }

    public abstract String getName();

    public abstract void describe();

    public abstract void use(Player p);

    public abstract void useOn(Player player, GameItem item);

    public abstract boolean hideInShadow();

    public abstract boolean isStatic();
}
