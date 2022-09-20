package space.nixus.maddoc;

import java.util.HashSet;
import java.util.Set;

public abstract class GameItem {

    private final Set<String> flags;

    protected GameItem() {
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

    public void handleEvent(String id) { }

    public abstract String getName();

    public abstract void describe(boolean lit, boolean carried);

    public abstract boolean canUse(Player p);

    public abstract void use(Player p);

    public abstract void useOn(Player player, GameItem item);

    public abstract boolean hideInShadow();

    public abstract boolean isStatic();
}
