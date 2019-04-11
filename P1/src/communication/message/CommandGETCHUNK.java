package communication.message;

import chunk.Chunk;
import communication.Command;

public class CommandGETCHUNK extends Command {
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
//GETCHUNK <Version> <SenderId> <FileId> <ChunkNo> <CRLF><CRLF>

}
