package initiators;

import FILES.Manager;
import chunk.Chunk;
import chunk.ChunkInfo;
import communication.CommandProtocol;
import utils.LOGGER;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class BackUpStrategy implements InitiatorStrategy {

    File file;
    int repDgree;

    public BackUpStrategy(File file, int repDgree) {
        this.file = file;
        this.repDgree = repDgree;

    }

    @Override
    public void execute() {

        try {
            byte[] loadedData = Files.readAllBytes(file.toPath());

            int nChunks = loadedData.length / CommandProtocol.BODY_MAX_SIZE + 1;

            LOGGER.info(file.getName() + " will be splitted into " + nChunks + " chunks.");

            byte[][] chunks = Manager.splitBytes(loadedData, CommandProtocol.BODY_MAX_SIZE);

            for (int i = 0; i < nChunks; i++) {
                Chunk chunk = new Chunk(new ChunkInfo(file.getName(), i), repDgree, chunks[i]);
                Thread t = new Thread(new ChunkStrategyAux(chunk));
                t.start();

                t.join();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
