package communication.message;

import chunk.Chunk;
import communication.CommadType;
import communication.Command;
import peer.Peer;

import java.io.IOException;
import java.net.DatagramPacket;

public class CommandSTORED extends Command {


    @Override
    public void executeMessage(Chunk chunk) {
        String header = CommadType.STORED + " " + VERSION;
        header += " " + Peer.getPeerID() ;
        header += " " + chunk.getChunkInfo().getFileID();
        header += " " + chunk.getChunkInfo().getChunkNo();
        header += " " + CRLF;
        header += CRLF;

        this.header = header.getBytes();

        try {
            this.dataPacket = new DatagramPacket(this.header, this.header.length, Peer.getMcListener().addr, Peer.getMcListener().port);
            Peer.getSocket().send(this.dataPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
