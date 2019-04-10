package communication.message;

import communication.CommadType;
import communication.Command;
import peer.Peer;
import utils.Helper;
import utils.Logger;

import java.io.IOException;
import java.net.DatagramPacket;


public class CommandPUTCHUNK extends Command {


    @Override
    public void constructMessage() {
        String header = CommadType.PUTCHUNK +
                " " + VERSION +
                " " + CRLF + CRLF;

        this.header = header.getBytes();
    }

    @Override
    public void addBody(byte[] body) {
        this.body = body;


    }

    @Override
    public void send() {
        byte[] c ;
        try {
            c = Helper.concatByteArrays(header, body);
            dataPacket = new DatagramPacket(c, c.length, Peer.getMdbListener().addr, Peer.getMdbListener().port);
            Peer.getSocket().send(this.dataPacket);

//            dataPacket = new DatagramPacket(header, header.length, Peer.getMdbListener().addr, Peer.getMdbListener().port);
//            Peer.getSocket().send(this.dataPacket);
        }
            catch (IOException e) {
            Logger.error("Sending PUTCHUNK to Control Multicast");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return new String(header) + "/"+ new String(body);
    }
}
