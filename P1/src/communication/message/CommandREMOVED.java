package communication.message;

import chunk.Chunk;
import communication.CommadType;
import communication.Command;
import peer.Peer;
import utils.LOGGER;

import java.io.IOException;
import java.net.DatagramPacket;

public class CommandREMOVED extends Command {

    //    REMOVED <Version> <SenderId> <FileId> <ChunkNo> <CRLF><CRLF>
    @Override
    public void executeMessage(Chunk chunk) {
        String header = CommadType.REMOVED+ " ";
        header += VERSION + " ";
        header += Peer.getPeerID() + " ";
        header += chunk.getChunkInfo().getFileID() + " ";
        header += chunk.getChunkInfo().getChunkNo() + " ";
        header += CRLF + CRLF;

        this.header = header.getBytes();

        try {
            dataPacket = new DatagramPacket(this.header, this.header.length, Peer.getMcListener().addr, Peer.getMcListener().port);
            Peer.getSocket().send(this.dataPacket);
        } catch (IOException e) {
            LOGGER.error("Sending GETCHUNKto MDB Multicast");
            e.printStackTrace();
        }
    }

}
