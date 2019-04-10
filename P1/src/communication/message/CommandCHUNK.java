package communication.message;

import communication.Command;

public class CommandCHUNK extends Command {
    @Override
    public void send() {

    }

    @Override
    public void constructMessage() {

    }

    @Override
    public void addBody(byte[] body) {

    }
//    CHUNK <Version> <SenderId> <FileId> <ChunkNo> <CRLF><CRLF><Body>
}
