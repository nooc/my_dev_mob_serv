package space.nixus.maddoc;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author Ben Bright <nooc@users.noreply.github.com>
 */
public interface GameStateIO {

    /**
     * Write state to writer.
     *
     * @param writer Output
     * @throws IOException Writer exception.
     */
    void saveState(JsonWriter writer) throws IOException;

    /**
     * Read state from object.
     *
     * @param cfg State config
     * @throws Exception Config exception.
     */
    void loadState(JsonObject cfg) throws Exception;
}
