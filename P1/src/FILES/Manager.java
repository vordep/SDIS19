package FILES;

import chunk.Chunk;
import peer.Peer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Manager {

    public static final String BASE_PATH =System.getProperty("user.dir")+"/";
    public static final String CHUNK_PATH = "CHUNKS/";

    public static byte[][] splitBytes(final byte[] data, final int chunkSize) {
        final int length = data.length;
        final byte[][] dest = new byte[(length + chunkSize - 1) / chunkSize][];
        int destIndex = 0;
        int stopIndex = 0;

        for (int startIndex = 0; startIndex + chunkSize <= length; startIndex += chunkSize) {
            stopIndex += chunkSize;
            dest[destIndex++] = Arrays.copyOfRange(data, startIndex, stopIndex);
        }

        if (stopIndex < length)
            dest[destIndex] = Arrays.copyOfRange(data, stopIndex, length);

        return dest;
    }

    public static void saveChunk(Chunk chunk){
        if (!folderExists(BASE_PATH + "PEER" + Peer.getPeerID()+ "/" + CHUNK_PATH ));
            createFolder(BASE_PATH + "PEER" + Peer.getPeerID()+ "/" + CHUNK_PATH );
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(BASE_PATH + "PEER" + Peer.getPeerID()+ "/" + CHUNK_PATH + chunk.getChunkInfo().toString());

            out.write(chunk.getData());
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean fileExists(String filePathString){
        File f = new File(BASE_PATH + "PEER" + Peer.getPeerID()+ "/" + CHUNK_PATH + filePathString);
        return f.isFile() && !f.isDirectory();
    }

    public static boolean folderExists(String name) {
        File file = new File(name);

        return file.exists() && file.isDirectory();
    }

    public static void createFolder(String name) {
        File file = new File(name);

        boolean mkdirs = file.mkdirs();
    }
    public static void deleteFile(String name){
        File f = new File(BASE_PATH + "PEER" + Peer.getPeerID()+ "/" + CHUNK_PATH + name);
        f.delete();

    }
}