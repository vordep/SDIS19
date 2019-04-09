package communication.message;

import communication.Message;
import communication.MessageType;
import peer.Peer;
import utils.Logger;

import java.io.IOException;
import java.net.DatagramPacket;

public class MessagePUTCHUNK extends Message {


    public MessagePUTCHUNK() {
        constructMessage();
    }

    @Override
    public void constructMessage() {
        String header = MessageType.STORED +
                " " + VERSION +
                " " + CRLF + CRLF;

        this.header = header.getBytes();

        dataPacket = new DatagramPacket(this.header,this.header.length, Peer.getMcListener().addr,Peer.getMcListener().port);

    }

    @Override
    public void addBody(byte[] body) {

    }

    @Override
    public void send() {
        try {
            Peer.getSocket().send(dataPacket);
        } catch (IOException e) {
            Logger.error("Sending PUTCHUNK to Control Multicast");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return header.toString() + body.toString();
    }
}
