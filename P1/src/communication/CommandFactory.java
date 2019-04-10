package communication;

import communication.message.CommandPUTCHUNK;
import communication.message.CommandSTORED;

public class CommandFactory {
    public static Command getMessage(CommadType type) {
        if (CommadType.STORED.equals(type)) {
            return new CommandSTORED();
        }else if(CommadType.PUTCHUNK.equals(type)){
            return new CommandPUTCHUNK();
        }

        return null;
    }
}
