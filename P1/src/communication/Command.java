package communication;

import chunk.Chunk;

import java.net.DatagramPacket;

public abstract class Command extends CommandProtocol {

    protected DatagramPacket dataPacket;
    protected byte[] header;
    protected byte[] body;

    public abstract void executeMessage(Chunk chunk);


}



