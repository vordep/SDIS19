package communication;

public enum CommadType {
    STORED("STORED"),
    PUTCHUNK("PUTCHUNK"),
    CHUNK("CHUNK"),
    GETCHUNK("GETCHUNK"),
    REMOVED("REMOVED"),
    DELETE("DELETE");

    private String commandType;

    CommadType(String commandType) {
        this.commandType = commandType;
    }

    public String getType() {
        return this.commandType;
    }
}
