package utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Helper {
    public static byte[] concatByteArrays(byte[] a, byte[] b) throws IOException {


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(a);
        outputStream.write(b);

        byte c[] = outputStream.toByteArray();

        return c;
    }
    public static void randomDelay(float min, float max){
        int random = (int)(max * Math.random() + min);
        try {
            Thread.sleep(random );
        } catch (InterruptedException e) {
            LOGGER.error("HELPER > randomDelay");
            e.printStackTrace();
        }
    }
}
