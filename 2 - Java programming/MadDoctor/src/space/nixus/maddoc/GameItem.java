package space.nixus.maddoc;

public interface GameItem {
    String getName();

    String getDescription(boolean lit);

    boolean canUse(Player p);

    boolean use(Player p);

    boolean hideInShadow();
}
