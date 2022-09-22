package space.nixus.maddoc;

public class RoomInventory extends Inventory {
    /** Room of this inventory. */
    private final Room room;

    public RoomInventory(Room room) {
        super();
        this.room = room;
    }

    @Override
    public void describe() {
        if (!content.isEmpty()) {
            var lit = room.isLit();
            for (var i : content.entrySet()) {
                if (i.getValue().hideInShadow() && !lit) continue;
                i.getValue().describe();
            }
        }
    }
}
