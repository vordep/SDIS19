package communication;

import java.net.DatagramPacket;
public abstract class  Message extends MessageProtocol{

    protected DatagramPacket dataPacket;
    protected byte[] header ;
    protected byte[] body ;

    public abstract void send();
    public abstract void constructMessage();
    public abstract void addBody(byte[] body);


}



