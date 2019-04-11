package handlers;

import FILES.Manager;
import chunk.Chunk;
import chunk.ChunkID;
import communication.CommadType;
import communication.Command;
import communication.CommandFactory;
import communication.CommandProtocol;

public class PutChunkStrategy implements HandlerStrategy {

    @Override
    public void execute(String[] headerTokens, byte[] body) {


        ChunkID chunkID = new ChunkID(headerTokens[CommandProtocol.FILEID],Integer.parseInt(headerTokens[CommandProtocol.CHUNKNO]));

        int repDegree = Integer.parseInt(headerTokens[CommandProtocol.REPDEGREE]);

        Chunk chunk = new Chunk(chunkID,repDegree,body);

        if(Manager.fileExists(chunkID.toString())){
            Command m = CommandFactory.getMessage(CommadType.STORED);
            m.constructMessage(chunk);
            m.send();
        }else{
            Manager.saveChunk(chunk);

        }





    }
}
