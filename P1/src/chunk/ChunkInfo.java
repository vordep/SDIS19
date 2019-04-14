package chunk;

import java.util.Objects;

public class ChunkInfo {
    String fileID;
    int chunkNo;


    public ChunkInfo(String fileID, int chunkNo) {
        this.fileID = fileID;
        this.chunkNo = chunkNo;
    }
    public ChunkInfo(String fileID) {
        this.fileID = fileID;
    }
    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public int getChunkNo() {
        return chunkNo;
    }

    public void setChunkNo(int chunkNo) {
        this.chunkNo = chunkNo;
    }

    @Override
    public String toString() {
        return fileID + "-" + chunkNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChunkInfo chunkInfo = (ChunkInfo) o;
        return chunkNo == chunkInfo.chunkNo &&
                Objects.equals(fileID, chunkInfo.fileID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileID, chunkNo);
    }
}
