package communication;

import chunk.Chunk;

import java.net.DatagramPacket;

public abstract class Command extends CommandProtocol {

    protected DatagramPacket dataPacket;
    protected byte[] header;
    protected byte[] body;
    protected CommadType type;

    public abstract void send();

    public abstract void constructMessage();
    public abstract void constructMessage(Chunk chunk);

    public abstract void addBody(byte[] body);


}



