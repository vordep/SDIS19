package handlers;

import chunk.Chunk;
import chunk.ChunkInfo;
import communication.CommadType;
import communication.CommandProtocol;
import peer.Peer;
import utils.LOGGER;

import java.io.*;
import java.net.DatagramPacket;
import java.security.MessageDigest;
import java.util.Arrays;

public class Handler implements Runnable {

    DatagramPacket datagramPacket;
    HandlerStrategy handlerStrategy;
    private String header;
    private String[] headerTokens;
    private byte[] body;
    private Chunk chunkToHandle;

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
        if(headerTokens.length > 3){
            extractBody();
        }
        if(body == null){
            body = "sas".getBytes();
        }
        this.handlerStrategy.execute(headerTokens,body);
    }

    private void buildChunk() {


        if(headerTokens.length >= 5){
            int repDegree = Integer.parseInt(headerTokens[CommandProtocol.REPDEGREE]);
        }
        if(headerTokens.length >= 4){
            ChunkInfo chunkInfo = new ChunkInfo(headerTokens[CommandProtocol.FILEID],Integer.parseInt(headerTokens[CommandProtocol.CHUNKNO]));
        }

    }

    private void getStrategy() {
        if (!extractHeaders()) {
            LOGGER.error("Error extracting headers of chunk ");
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
            case DELETE:
                this.handlerStrategy = new DeleteStrategy();
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

    public static final String getFileID(File file) {
        String str = file.getName() + file.lastModified() + Peer.getPeerID();

        return sha256(str);
    }

    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

}


