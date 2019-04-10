package initiators;

import chunk.Chunk;
import communication.CommadType;
import communication.Command;
import communication.CommandFactory;

import static java.lang.Thread.sleep;

public class ChunkStrategyAux implements Runnable {

    private static final int maxTries = 5 ;
    private static final int waitingTimeMSeconds  = 500;
    private Chunk chunkToBackup;

    ChunkStrategyAux(Chunk chunkToBackup) {
        this.chunkToBackup = chunkToBackup;
    }


    @Override
    public void run() {
        System.out.println(chunkToBackup.getId().toString());

        try {
            sleep(500);
            Command m = CommandFactory.getMessage(CommadType.PUTCHUNK);
            m.constructMessage();
            m.addBody(chunkToBackup.getData());
            m.send();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }


}
