package communication;

public enum MessageType {
    STORED("STORED"),
    PUTCHUNK("PUTCHUNK");

    private String messageType;

    MessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getType() {
        return this.messageType;
    }
}
