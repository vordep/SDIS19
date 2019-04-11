package communication.message;

import chunk.Chunk;
import communication.CommadType;
import communication.Command;
import peer.Peer;
import utils.Helper;
import utils.Logger;

import java.io.IOException;
import java.net.DatagramPacket;

public class CommandPUTCHUNK extends Command {


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
    public void constructMessage(Chunk chunk) {
        String header = CommadType.PUTCHUNK + " ";
        header += VERSION + " ";
        header += Peer.getPeerID() + " ";
        header += chunk.getId().getFileID() + " ";
        header += chunk.getId().getChunkNo() + " ";
        header += chunk.getReplicationDegree()+ " ";
        header += CRLF + CRLF;

        this.header = header.getBytes();
    }

    @Override
    public void constructMessage() {

        String header = CommadType.PUTCHUNK + " ";
        header += VERSION + " ";
        header += Peer.getPeerID();
        //header += " " + chunkID.getFileID();
        //header += " " + chunkID.getChunkNo();
        header += CRLF + CRLF;


        this.header = header.getBytes();
    }

    @Override
    public void addBody(byte[] body) {
        this.body = body;
    }
}
