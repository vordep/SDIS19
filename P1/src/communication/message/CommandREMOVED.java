package communication.message;

import chunk.Chunk;
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
    public void constructMessage(Chunk chunk) {

    }

    @Override
    public void addBody(byte[] body) {

    }


}
