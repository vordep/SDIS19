package communication.message;

import chunk.Chunk;
import communication.CommadType;
import communication.Command;
import peer.Peer;
import utils.LOGGER;

import java.io.IOException;
import java.net.DatagramPacket;

public class CommandDELETE extends Command {
    //    DELETE <Version> <SenderId> <FileId> <CRLF><CRLF>

    @Override
    public void executeMessage(Chunk chunk) {

        String header = CommadType.DELETE + " ";
        header += VERSION + " ";
        header += Peer.getPeerID() + " ";
        header += chunk.getChunkInfo().getFileID() + " ";
        header += CRLF + CRLF;

        this.header = header.getBytes();

        byte[] messageData = this.header;

        try {
            dataPacket = new DatagramPacket(messageData, messageData.length, Peer.getMcListener().addr, Peer.getMcListener().port);
            Peer.getSocket().send(this.dataPacket);
        } catch (IOException e) {
            LOGGER.error("Sending PUTCHUNK to MDB Multicast");
            e.printStackTrace();
        }
    }
}
