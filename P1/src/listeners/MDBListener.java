package listeners;

import handlers.Handler;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class MDBListener extends Listener {
    public MDBListener(InetAddress addr, int port) {
        super(addr, port);
    }

    @Override
    public void handler(DatagramPacket dataPacket) {
        new Thread(new Handler(dataPacket)).start();

    }

    // Start Log of PUTCHUNK


}


