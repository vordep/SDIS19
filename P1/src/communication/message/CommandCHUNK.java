package communication.message;

import chunk.Chunk;
import communication.CommadType;
import communication.Command;
import peer.Peer;
import utils.Helper;
import utils.LOGGER;

import java.io.IOException;
import java.net.DatagramPacket;

public class CommandCHUNK extends Command {
    //CHUNK <Version> <SenderId> <FileId> <ChunkNo> <CRLF><CRLF><Body>
    @Override
    public void executeMessage(Chunk chunk) {

        String header = CommadType.CHUNK + " ";
        header += VERSION + " ";
        header += Peer.getPeerID() + " ";
        header += chunk.getChunkInfo().getFileID() + " ";
        header += chunk.getChunkInfo().getChunkNo() + " ";
        header += CRLF + CRLF;

        this.header = header.getBytes();
        this.body = chunk.getData();

        byte[] messageData;

        try {
            messageData = Helper.concatByteArrays(this.header, body);
            dataPacket = new DatagramPacket(messageData, messageData.length, Peer.getMdrListener().addr, Peer.getMdrListener().port);
            Peer.getSocket().send(this.dataPacket);
        } catch (IOException e) {
            LOGGER.error("Sending PUTCHUNK to MDB Multicast");
            e.printStackTrace();
        }
    }
}
