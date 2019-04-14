package FILES;

import java.util.ArrayList;
import java.util.HashMap;

public class FileDatabase {

    private volatile HashMap<String, ArrayList<String>> FilesBackedUpByPeer;

    public FileDatabase(HashMap<String, ArrayList<String>> filesBackedUpByPeer) {
        FilesBackedUpByPeer = filesBackedUpByPeer;
    }

}
