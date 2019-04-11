package communication.message;

import chunk.Chunk;
import communication.CommadType;
import communication.Command;
import peer.Peer;
import utils.Helper;
import utils.Logger;

import java.io.IOException;
import java.net.DatagramPacket;

public class CommandCHUNK extends Command {
    //CHUNK <Version> <SenderId> <FileId> <ChunkNo> <CRLF><CRLF><Body>
    @Override
    public void send() {
        byte[] c;
        try {
            c = Helper.concatByteArrays(header, body);
            dataPacket = new DatagramPacket(c, c.length, Peer.getMdbListener().addr, Peer.getMdbListener().port);
            Peer.getSocket().send(this.dataPacket);
        } catch (IOException e) {
            Logger.error("Sending PUTCHUNK to Control Multicast");
            e.printStackTrace();
        }
    }

    @Override
    public void constructMessage() {

    }

    @Override
    public void constructMessage(Chunk chunk) {
        String header = CommadType.CHUNK + " ";
        header = PROTOCOL_VERSION + " ";
        header = Peer.getPeerID() + " ";
        header = chunk.getId().getFileID() + " ";
        header = chunk.getReplicationDegree()+ " ";
        header = CommadType.CHUNK + " ";

    }

    @Override
    public void addBody(byte[] body) {

    }

}
