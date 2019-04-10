package utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Helper {
    public static byte[] concatByteArrays(byte[] a, byte[]b) throws IOException {


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write( a );
        outputStream.write( b );

        byte c[] = outputStream.toByteArray( );

        return c;
    }
}
