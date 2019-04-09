package communication.message;

import communication.Message;
import communication.MessageType;
import peer.Peer;

import java.io.IOException;
import java.net.DatagramPacket;

public class MessageSTORED extends Message {


    @Override
    public void send() {
        try {
            this.dataPacket = new DatagramPacket(header,header.length, Peer.getMcListener().addr,Peer.getMcListener().port);
            Peer.getSocket().send(this.dataPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void constructMessage() {
        String header = MessageType.STORED + " " + VERSION;
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
