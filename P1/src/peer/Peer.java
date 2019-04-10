package peer;


import initiators.BackUpStrategy;
import initiators.Initiator;
import listeners.Listener;
import listeners.MCListener;
import listeners.MDBListener;
import utils.Logger;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Peer {

    private static String mcAddressName = "224.0.0.0";

    private static int MCPort = 8000;
    private static int MDBPort = 8001;
    private static int mcrPort = 8002;

    private static Listener l;
    private static MCListener mcListener;
    private static MDBListener mdbListener;

    private static MulticastSocket socket;
    private static int PeerID = 1 ;


    public static MCListener getMcListener() {
        return mcListener;
    }

    public static MDBListener getMdbListener() {
        return mdbListener;
    }

    public static void main(String[] args) {

        Logger.info("Server is starting ");

        Logger.info("Opening Listeners ");

        InetAddress addr;
        try {
            socket = new MulticastSocket();
        } catch (IOException e) {
            Logger.error("Getting socket Multicast ");
            e.printStackTrace();
        }


        try {
            addr = InetAddress.getByName(mcAddressName);
            mcListener = new MCListener(addr, MCPort);
            mdbListener = new MDBListener(addr, MDBPort);
            new Thread(mdbListener).start();
            new Thread(mcListener).start();
//            sendUDPMessage("STORED WORLD", "224.0.0.0", 8001);
            //sendUDPMessage("PUTCHUNK WORLD", "224.0.0.0", 8000);

        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File("/Users/guedes/Desktop/SDIS/P1/P1/FILEs/digimon.jpg");

        if(file.exists()) {
            Initiator init = new Initiator(new File("../FILES/digimon.jpg"), new BackUpStrategy(new File("/Users/guedes/Desktop/SDIS/P1/P1/FILEs/digimon.jpg"), 2));
            new Thread(init).start();
        }

    }

    public static MulticastSocket getSocket() {
        return socket;
    }

    public static void sendUDPMessage(String message,
                                      String ipAddress, int port) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress group = InetAddress.getByName(ipAddress);
        byte[] msg = message.getBytes();
        DatagramPacket packet = new DatagramPacket(msg, msg.length,
                group, port);
        socket.send(packet);

    }
}

