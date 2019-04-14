package handlers;

import FILES.Manager;
import chunk.ChunkInfo;
import communication.CommandProtocol;
import peer.Peer;
import utils.LOGGER;

public class DeleteStrategy implements HandlerStrategy {
    @Override
    public void execute(String[] headerTokens, byte[] body) {
        LOGGER.info("DELETE HANDLER");
        String fileID = headerTokens[CommandProtocol.FILEID];
        for (ChunkInfo chunkInfo : Peer.getChunkDB().getKeySet()){
            if(chunkInfo.getFileID().equals(fileID) ){

                Manager.deleteFile(fileID);
                //TODO DELETE FILE
            }
        }
    }
}
