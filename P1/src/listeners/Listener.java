package listeners;

import utils.LOGGER;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public abstract class Listener implements Runnable {

    public static final int maxPacketSize = 70000;
    public InetAddress addr;
    public int port;
    MulticastSocket socket;


    public Listener(InetAddress addr, int port) {
        this.addr = addr;
        this.port = port;
    }

    //https://www.developer.com/java/data/how-to-multicast-using-java-sockets.html
    public void openConnection() {
        LOGGER.info("Opening Multicast Socket");

        try {
            socket = new MulticastSocket(port);
            socket.setTimeToLive(1);
            socket.joinGroup(addr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void closeConnection() {
        LOGGER.info("Closing Multicast Socket ");
        try {
            this.socket.leaveGroup(addr);
        } catch (IOException e) {
            LOGGER.error("Closing Connection");
            e.printStackTrace();
        }
        this.socket.close();
    }

    @Override
    public void run() {

        openConnection();

        //read
        byte[] buffer = new byte[maxPacketSize];
        boolean stop = false;

        while (!stop) {
            try {
                DatagramPacket data = new DatagramPacket(buffer, buffer.length);
                socket.receive(data);
                handler(data);

            } catch (IOException e) {
                LOGGER.error("Receiving packet");
                e.printStackTrace();
            }

        }
        closeConnection();
    }

    public abstract void handler(DatagramPacket dataPacket);

}
