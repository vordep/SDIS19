package communication;

public class MessageProtocol {
    public static int MESSAGE_TYPE = 0 ;
    public static int PROTOCOL_VERSION= 1 ;
    public static int SENDERID = 2 ;
    public static int FILEID = 3 ;
    public static int REPDEGREE = 4 ;

    public static final String VERSION = "1.0";
    public static final String CR = "\r";
    public static final String LF = "\n";
    public static final String CRLF = CR + LF;

    public static final int BODY_MAX_SIZE = 64000;


}
