package space.nixus.maddoc;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

public class Inventory implements Serializable {

    private HashMap<String,GameItem> content;

    public Inventory() {
        content = new HashMap<>();
    }

    public void describe() {

    }

    public boolean contains(String item) {
        return content.containsKey(item);
    }
}
