package handlers;

import chunk.Chunk;
import chunk.ChunkInfo;
import communication.CommandProtocol;
import peer.Peer;
import utils.LOGGER;

public class StoredStrategy implements HandlerStrategy {

    @Override
    public void execute(String[] headerTokens, byte[] body) {


        ChunkInfo chunkInfo = new ChunkInfo(headerTokens[CommandProtocol.FILEID],Integer.parseInt(headerTokens[CommandProtocol.CHUNKNO]));
        LOGGER.info("Start Stored Hanlder " + chunkInfo.toString());

        int senderID = Integer.parseInt(headerTokens[CommandProtocol.SENDERID]);
        Chunk chunk = new Chunk(headerTokens[CommandProtocol.FILEID],Integer.parseInt(headerTokens[CommandProtocol.CHUNKNO]));
        LOGGER.info("Stored SenderID :" + senderID);
        //Looging store

        if(Peer.getMcListener().isStoringChunk(chunk)){
            Peer.getMcListener().store(chunk,senderID);
        }

        if(Peer.getChunkDB().hasChunk(chunkInfo)){
            Peer.getChunkDB().addMirror(chunkInfo,senderID);
        }

    }
}
