package handlers;

import java.net.DatagramPacket;

public class PutChunkStrategy implements HandlerStrategy {

    @Override
    public void execute(DatagramPacket datagramPacket) {
        System.out.println("Handled");
    }
}
