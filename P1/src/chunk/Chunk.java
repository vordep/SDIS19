package chunk;

public class Chunk {
    public  static int MAX_SIZE = 64000;

    ChunkID id;
    int replicationDegree;
    byte[] data;

    public Chunk(ChunkID id, int replicationDegree, byte[] data) {
        this.id = id;
        this.replicationDegree = replicationDegree;
        this.data = data;
    }

    public Chunk(String fileID,int chunkNumber,int replicationDegree, byte[] data) {
        this.id = new ChunkID(fileID,chunkNumber);
        this.replicationDegree = replicationDegree;
        this.data = data;
    }

    public ChunkID getId() {
        return id;
    }

    public void setId(ChunkID id) {
        this.id = id;
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
