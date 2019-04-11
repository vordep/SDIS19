package communication.message;

import chunk.Chunk;
import communication.CommadType;
import communication.Command;
import peer.Peer;

import java.io.IOException;
import java.net.DatagramPacket;

public class CommandSTORED extends Command {


    @Override
    public void send() {
        try {
            this.dataPacket = new DatagramPacket(header, header.length, Peer.getMcListener().addr, Peer.getMcListener().port);
            Peer.getSocket().send(this.dataPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void constructMessage(Chunk chunk) {
        String header = CommadType.STORED + " " + VERSION;
        header += " " + chunk.getId().getFileID();
        header += " " + chunk.getId().getChunkNo();
        header += " " + CRLF;
        header += CRLF;

        this.header = header.getBytes();
    }

    @Override
    public void constructMessage() {
        String header = CommadType.STORED + " " + VERSION;
        //header += " " + chunkID.getFileID();
        //header += " " + chunkID.getChunkNo();
        header += " " + CRLF;
        header += CRLF;

        this.header = header.getBytes();
    }

    @Override
    public void addBody(byte[] body) {

    }
}
