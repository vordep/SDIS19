package handlers;

import communication.MessageType;
import utils.Logger;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;

public class Handler implements Runnable {

    DatagramPacket datagramPacket;
    HandlerStrategy handlerStrategy;
    private String header;
    private String[] headerTokens;
    private byte[] body;


    public Handler() {
        header = null;
        headerTokens = null;
        body = null;
        datagramPacket = null;

    }

    public Handler(DatagramPacket datagramPacket) {
        this.datagramPacket = datagramPacket;
        this.getStrategy();
    }

    @Override
    public void run() {
        this.handlerStrategy.execute();
    }

    private void getStrategy() {
        if (!extractHeaders()) {
            Logger.error("Error extracting headers of chunk ");
        }

        MessageType messageType = MessageType.valueOf(headerTokens[0]);
        switch (messageType) {
            case STORED:
                this.handlerStrategy = new StoredStrategy();
                break;
            case PUTCHUNK:
                this.handlerStrategy = new PutChunkStrategy();
                break;
        }
    }

    private boolean extractHeaders() {
        ByteArrayInputStream stream = new ByteArrayInputStream(datagramPacket.getData());
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(stream));
        try {
            header = reader.readLine();
            System.out.println(header);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        headerTokens = header.split("[ ]+");
        return true;
    }
}


