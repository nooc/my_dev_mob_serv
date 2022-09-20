package space.nixus.maddoc;

import java.io.Serializable;

public class RoomInventory extends Inventory implements Serializable {

    public static final String TYPE = "room";

    private Room room;

    public RoomInventory(Room room) {
        super();
        this.room = room;
    }

    @Override
    public void describe() {
        if (!content.isEmpty()) {
            var lit = room.isLit();
            for (var i : content.entrySet()) {
                if(i.getValue().hideInShadow() && !lit) continue;

                Game.fmt0("[%s]: ", i.getKey());
                i.getValue().describe();
            }
        }
    }

    public String invType() {
        return TYPE;
    }
}
