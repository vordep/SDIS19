package handlers;

import FILES.Manager;
import chunk.Chunk;
import chunk.ChunkInfo;
import communication.CommadType;
import communication.Command;
import communication.CommandFactory;
import communication.CommandProtocol;
import peer.Peer;
import utils.Helper;
import utils.LOGGER;

public class PutChunkStrategy implements HandlerStrategy {

    @Override
    public void execute(String[] headerTokens, byte[] body) {

        ChunkInfo chunkInfo = new ChunkInfo(headerTokens[CommandProtocol.FILEID],Integer.parseInt(headerTokens[CommandProtocol.CHUNKNO]));
        int repDegree = Integer.parseInt(headerTokens[CommandProtocol.REPDEGREE]);

        Chunk chunk = new Chunk(chunkInfo,repDegree,body);
        Command m = CommandFactory.getMessage(CommadType.STORED);

        if(Manager.fileExists(chunkInfo.toString())){
            m.executeMessage(chunk);
        }else{
            Peer.getMcListener().listenToStore(chunk);
            Helper.randomDelay(0,400);
            int storeNumReplies = Peer.getMcListener().getStoreNumReplies(chunk);
            LOGGER.info("Chunk file " +chunk.getChunkInfo().toString() +" : NumOfStores" + storeNumReplies);

            if(storeNumReplies < repDegree ){
                Manager.saveChunk(chunk);
                m.executeMessage(chunk);
            }


        }
    }
}
