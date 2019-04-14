package communication.message;

import chunk.Chunk;
import communication.CommadType;
import communication.Command;
import peer.Peer;
import utils.Helper;
import utils.LOGGER;

import java.io.IOException;
import java.net.DatagramPacket;

public class CommandPUTCHUNK extends Command {

    @Override
    public void executeMessage(Chunk chunk) {
        String header = CommadType.PUTCHUNK + " ";
        header += VERSION + " ";
        header += Peer.getPeerID() + " ";
        header += chunk.getChunkInfo().getFileID() + " ";
        header += chunk.getChunkInfo().getChunkNo() + " ";
        header += chunk.getReplicationDegree()+ " ";
        header += CRLF + CRLF;

        this.header = header.getBytes();
        this.body = chunk.getData();

        byte[] messageData;

        try {
            messageData = Helper.concatByteArrays(this.header, body);
            dataPacket = new DatagramPacket(messageData, messageData.length, Peer.getMdbListener().addr, Peer.getMdbListener().port);
            Peer.getSocket().send(this.dataPacket);
        } catch (IOException e) {
            LOGGER.error("Sending PUTCHUNK to MDB Multicast");
            e.printStackTrace();
        }
    }
}
