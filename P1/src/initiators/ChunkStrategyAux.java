package initiators;

import chunk.Chunk;

public class ChunkStrategyAux implements Runnable {

    private static final int maxTries = 5 ;
    private static final int waitingTimeMSeconds  = 500;
    private Chunk chunkToBackup;

    public ChunkStrategyAux(Chunk chunkToBackup) {
        this.chunkToBackup = chunkToBackup;
    }


    @Override
    public void run() {


    }


}
