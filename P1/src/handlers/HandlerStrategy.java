package handlers;

public interface HandlerStrategy {

    void execute(String[] headerTokens, byte[]body);

}
