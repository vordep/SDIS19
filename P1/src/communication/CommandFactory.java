package communication;

import communication.message.CommandGETCHUNK;
import communication.message.CommandPUTCHUNK;
import communication.message.CommandSTORED;

public class CommandFactory {
    public static Command getMessage(CommadType type) {
        if (CommadType.STORED.equals(type)) {
            return new CommandSTORED();
        } else if (CommadType.PUTCHUNK.equals(type)) {
            return new CommandPUTCHUNK();
        } else if (CommadType.GETCHUNK.equals(type)) {
            return new CommandGETCHUNK();
        } else if (CommadType.CHUNK.equals(type)) {
            return new CommandPUTCHUNK();
        } else if (CommadType.REMOVED.equals(type)) {
            return new CommandPUTCHUNK();
        } else if (CommadType.DELETE.equals(type)) {
            return new CommandPUTCHUNK();
        }

        return null;
    }
}
