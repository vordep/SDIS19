package communication;

public enum CommadType {
    STORED("STORED"),
    PUTCHUNK("PUTCHUNK"),
    CHUNK("CHUNK"),
    GETCHUNK("GETCHUNK"),
    REMOVED("REMOVED"),
    DELETE("DELETE");

    private String messageType;

    CommadType(String messageType) {
        this.messageType = messageType;
    }

    public String getType() {
        return this.messageType;
    }
}
