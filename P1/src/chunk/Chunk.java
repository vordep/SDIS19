package chunk;

public class Chunk {
    public static int MAX_SIZE = 64000;

    ChunkInfo chunkInfo;
    int replicationDegree;
    byte[] data;

    public Chunk(ChunkInfo chunkInfo, int replicationDegree, byte[] data) {
        this.chunkInfo = chunkInfo;
        this.replicationDegree = replicationDegree;
        this.data = data;
    }

    public Chunk(String fileID, int chunkNumber, int replicationDegree, byte[] data) {
        this.chunkInfo = new ChunkInfo(fileID, chunkNumber);
        this.replicationDegree = replicationDegree;
        this.data = data;
    }
    public Chunk(String fileID) {
        this.chunkInfo = new ChunkInfo(fileID);
    }
    public Chunk(String fileID,int chunkNumber){
        this.chunkInfo = new ChunkInfo(fileID,chunkNumber);
    }

    public ChunkInfo getChunkInfo() {
        return chunkInfo;
    }

    public void setChunkInfo(ChunkInfo chunkInfo) {
        this.chunkInfo = chunkInfo;
    }

    public int getReplicationDegree() {
        return replicationDegree;
    }

    public void setReplicationDegree(int replicationDegree) {
        this.replicationDegree = replicationDegree;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
