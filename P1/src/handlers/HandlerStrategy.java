package handlers;

import java.net.DatagramPacket;

public interface HandlerStrategy {

    void execute(DatagramPacket datagramPacket);
}
