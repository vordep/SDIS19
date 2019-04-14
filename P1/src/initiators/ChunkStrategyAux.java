package initiators;

import chunk.Chunk;
import communication.CommadType;
import communication.Command;
import communication.CommandFactory;
import peer.Peer;
import utils.Helper;
import utils.LOGGER;

public class ChunkStrategyAux implements Runnable {

    private static int maxTries = 5;
    private static int waitingTimeMSeconds = 1000;
    private Chunk chunkToBackup;

    ChunkStrategyAux(Chunk chunkToBackup) {
        this.chunkToBackup = chunkToBackup;
    }


    @Override
    public void run() {


        int tries = 1;
        boolean done;
        do {
            Peer.getMcListener().listenToStore(chunkToBackup);

            Command m = CommandFactory.getMessage(CommadType.PUTCHUNK);
            m.executeMessage(chunkToBackup);

            Helper.randomDelay(0,waitingTimeMSeconds);

            int numStores= Peer.getMcListener().getStoreNumReplies(chunkToBackup);
            LOGGER.info("Chunk file " + chunkToBackup.getChunkInfo().toString() +" : Perceived Rep Degree " + numStores);
            if(numStores < chunkToBackup.getReplicationDegree()){
                Peer.getMcListener().removeChunkInfo(chunkToBackup);
                if(tries >= maxTries){
                    done=true;
                }
                else {
                    done = false;
                    waitingTimeMSeconds = waitingTimeMSeconds* 2;
                }

            }else {
                done=true;
            }
        }
        while(!done);






        //ListenToStores of chunk

    }


}
