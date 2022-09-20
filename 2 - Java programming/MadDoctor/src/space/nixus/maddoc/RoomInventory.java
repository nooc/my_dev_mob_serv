package space.nixus.maddoc;

public class RoomInventory extends Inventory {

    public static final String TYPE = "room";

    private Room room;

    public RoomInventory(Room room) {
        super();
        this.room = room;
    }

    @Override
    public void describe() {
        if(!content.isEmpty()) {
            Game.fmt("You see...");
            var lit = room.isLit();
            for (var i: content.entrySet()) {
                Game.fmt0("[%s]: ", i.getKey());
                i.getValue().describe(lit, false);
            }
        }
    }

    public String invType() { return TYPE; }
}
