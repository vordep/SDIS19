package handlers;

import communication.CommadType;
import communication.CommandProtocol;
import utils.Logger;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.util.Arrays;

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
        extractBody();
        this.handlerStrategy.execute(headerTokens,body);
    }

    private void getStrategy() {
        if (!extractHeaders()) {
            Logger.error("Error extracting headers of chunk ");
        }

        CommadType commadType = CommadType.valueOf(headerTokens[0]);
//        System.out.println(commadType.toString());
        switch (commadType) {
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

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        headerTokens = header.split("[ ]+");
        return true;
    }
    private void extractBody() {
        /*
         * In order to support other implementations which may use additional
         * header lines, the correct way to extract the body is to read header
         * lines until an empty line is read. After that, the body start
         * position is the sum of the length of the lines read, plus the length
         * of the <CRLF> of each line.
         */
        ByteArrayInputStream stream = new ByteArrayInputStream(datagramPacket.getData());
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(stream));

        String line = null;
        int headerLinesLengthSum = 0;
        int numLines = 0;

        do {
            try {
                line = reader.readLine();

                headerLinesLengthSum += line.length();

                numLines++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!line.isEmpty());

        int bodyStartIndex = headerLinesLengthSum + numLines
                * CommandProtocol.CRLF.getBytes().length;

        body = Arrays.copyOfRange(datagramPacket.getData(), bodyStartIndex,
                datagramPacket.getLength());
    }

}


