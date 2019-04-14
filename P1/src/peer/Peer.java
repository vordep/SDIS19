package peer;


import chunk.ChunkDatabase;
import initiators.DeleteStrategy;
import initiators.Initiator;
import listeners.MCListener;
import listeners.MDBListener;
import listeners.MDRListener;
import utils.LOGGER;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Peer {

    private static String mcAddressName = "224.0.0.0";
    private static String mdbAddressName = "224.0.0.0";
    private static String mdrAddressName = "224.0.0.0";

    private static int MCPort = 8000;
    private static int MDBPort = 8001;
    private static int MDRPort = 8002;

    private static MCListener mcListener;
    private static MDBListener mdbListener;
    private static MDRListener mdrListener;

    private static MulticastSocket socket;

    private static int PeerID ;


    private static volatile ChunkDatabase chunkDB;

    private static double Protocol_version = 1.0;

    public static MCListener getMcListener() {
        return mcListener;
    }

    public static MDBListener getMdbListener() {
        return mdbListener;
    }

    public static MDRListener getMdrListener() {
        return mdrListener;
    }

    public static int execute;
    public static void main(String[] args) {
        if(!validateArgs(args)){
            return;
        }
        LOGGER.info("Server is starting ");

        LOGGER.info("Opening Listeners ");

        InetAddress addr;
        try {
            socket = new MulticastSocket();
        } catch (IOException e) {
            LOGGER.error("Getting socket Multicast ");
            e.printStackTrace();
        }

        chunkDB = new ChunkDatabase();
        try {
            addr = InetAddress.getByName(mcAddressName);
            mcListener = new MCListener(addr, MCPort);
            addr = InetAddress.getByName(mdbAddressName);
            mdbListener = new MDBListener(addr, MDBPort);
            addr = InetAddress.getByName(mdrAddressName);
            mdrListener = new MDRListener(addr, MDRPort);
            new Thread(mdbListener).start();
            new Thread(mcListener).start();
            new Thread(mdrListener).start();
            if (execute == 1 ){
                File file = new File("/Users/guedes/Desktop/SDIS/P1/P1/FILEs/digimon.jpg");

                if (file.exists()) {
//                    Initiator init = new Initiator(new BackUpStrategy(new File("/Users/guedes/Desktop/SDIS/P1/P1/FILEs/digimon.jpg"),2));
                    Initiator init = new Initiator(new DeleteStrategy(new File("/Users/guedes/Desktop/SDIS/P1/P1/FILEs/digimon.jpg")));
                    new Thread(init).start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MulticastSocket getSocket() {
        return socket;
    }

    public static int getPeerID() {
        return PeerID;
    }

    public Peer(int peerID) {
        PeerID = peerID;
    }
    private static boolean validateArgs(String[] args ){

        if(args.length == 0 ){
            printUSage();

            LOGGER.info("Running with Default values");
            LOGGER.info("Control : " + mcAddressName + ":" + MCPort);
            LOGGER.info("BACK UP : " + mdbAddressName + ":" + MDBPort);
            LOGGER.info("RECOVER : " + mdrAddressName + ":" + MDRPort);
            LOGGER.info("PeerID  : "+ PeerID);
            return true;
        }

        if (args.length == 1 ){
            execute = 1 ;
            PeerID = 1;
            return true;
        }
        if (args.length == 3 ){
            Protocol_version = Integer.parseInt(args[0]);
            PeerID = Integer.parseInt(args[1]);
        }
        if (args.length == 9 ){
            Protocol_version = Integer.parseInt(args[0]);
            PeerID = Integer.parseInt(args[1]);
            mcAddressName = args[3];
            MCPort = Integer.parseInt(args[4]);
            mdbAddressName = args[5];
            MDBPort = Integer.parseInt(args[6]);
            mdrAddressName = args[7];
            MDRPort = Integer.parseInt(args[8]);
        }
        return true;
    }

    private static void printUSage() {

        System.out.println("Usage: ");
        System.out.println("\tjava peer.Peer");
        System.out.println("\tjava peer.Peer MC_ADDRESS MC_PORT MDB_ADDRESS MDB_PORT MDR_ADDRESS MDR_PORT");
        System.out.println();
        System.out.println("Usage: PEER WITH RMI FOR INITIATOR");
        System.out.println("\tjava peer.Peer PROTOCOL_VERSION PEERID RMI_OBJECT");
        System.out.println("\tjava peer.Peer PROTOCOL_VERSION PEERID RMI_OBJECT  MC_ADDRESS MC_PORT MDB_ADDRESS MDB_PORT MDR_ADDRESS MDR_PORT");

    }


    public static ChunkDatabase getChunkDB() {
        return chunkDB;
    }

}

