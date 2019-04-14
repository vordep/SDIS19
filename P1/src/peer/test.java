package peer;

public class test {
    public static void main(String[] args) {
        Peer peer1 = new Peer(1);
        Peer peer2 = new Peer(2);
        String[] ghost = new String[]{};
        peer1.main(ghost);
        peer2.main(ghost);

    }
}
