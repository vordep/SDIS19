package listeners;

import handlers.Handler;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class MDRListener extends Listener {


    public MDRListener(InetAddress addr, int port) {
        super(addr, port);
    }

    @Override
    public void handler(DatagramPacket dataPacket) {
        new Thread(new Handler()).start();
    }


}
