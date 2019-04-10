package communication.message;

import communication.Command;

public class CommandREMOVED extends Command {

    //    REMOVED <Version> <SenderId> <FileId> <ChunkNo> <CRLF><CRLF>
    @Override
    public void send() {

    }

    @Override
    public void constructMessage() {

    }

    @Override
    public void addBody(byte[] body) {

    }


}
