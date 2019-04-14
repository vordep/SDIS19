package chunk;

import utils.LOGGER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ChunkDatabase {
    private volatile HashMap<ChunkInfo, ArrayList<Integer>> chunkDataBase;
    public ChunkDatabase(){
        chunkDataBase = new HashMap<>();
    }

    public synchronized boolean hasChunk(ChunkInfo chunkInfo){
        return chunkDataBase.containsKey(chunkInfo);
    }
    public synchronized void addChunk(ChunkInfo chunkInfo,int senderId){
        if(!chunkDataBase.containsKey(chunkInfo)){
            chunkDataBase.put(chunkInfo,new ArrayList<>(senderId));
        }
    }
    public synchronized void addMirror(ChunkInfo chunkInfo, int senderId){
        if(!chunkDataBase.containsKey(chunkInfo) ){
            LOGGER.error("Could find Chunk ");
            return;
        }
        if(!chunkDataBase.get(chunkInfo).contains(senderId))
        {
            chunkDataBase.get(chunkInfo).add(senderId);
        }
    }
    public synchronized void removeChunk(ChunkInfo chunkInfo){
        chunkDataBase.remove(chunkInfo);
    }
    public synchronized void removeChunkMirror(ChunkInfo chunkInfo, int senderId){
        if(!chunkDataBase.containsKey(chunkInfo) ){
            LOGGER.error("Could find Chunk ");
            return;
        }
        if(!chunkDataBase.get(chunkInfo).contains(senderId))
        {
            chunkDataBase.get(chunkInfo).remove(senderId);
        }
    }
    public synchronized Set<ChunkInfo > getKeySet(){
        return chunkDataBase.keySet();

    }
}
