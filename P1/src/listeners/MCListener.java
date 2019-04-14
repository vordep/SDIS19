package listeners;

import chunk.Chunk;
import chunk.ChunkInfo;
import handlers.Handler;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Hashtable;

public class MCListener extends Listener {
    public MCListener(InetAddress addr, int port) {
        super(addr, port);
        this.storedMessagesLogg= new Hashtable<ChunkInfo, ArrayList<Integer>>();
    }

    @Override
    public void handler(DatagramPacket dataPacket) {
        new Thread(new Handler(dataPacket)).start();

    }

    //Hashtable containing the store commands on the mc channel
    private volatile Hashtable<ChunkInfo, ArrayList<Integer>> storedMessagesLogg;

    public synchronized void listenToStore(Chunk chunk){
        if(!storedMessagesLogg.containsKey(chunk.getChunkInfo())){
            storedMessagesLogg.put(chunk.getChunkInfo(),new ArrayList<Integer>());
        }
    }
    public synchronized int getStoreNumReplies(Chunk chunk){
        return storedMessagesLogg.get(chunk.getChunkInfo()).size();
    }
    public synchronized void removeChunkInfo(Chunk chunk){
        storedMessagesLogg.remove(chunk.getChunkInfo());
    }
    public synchronized boolean isStoringChunk(Chunk chunk){

        return storedMessagesLogg.containsKey(chunk.getChunkInfo());
    }
    public synchronized void store(Chunk chunk, int senderID){
        if(!storedMessagesLogg.get(chunk.getChunkInfo()).contains(senderID))
            storedMessagesLogg.get(chunk.getChunkInfo()).add(senderID);
    }
}


