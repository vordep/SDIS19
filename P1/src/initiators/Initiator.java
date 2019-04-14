package initiators;

import java.io.File;

public class Initiator implements Runnable {

    InitiatorStrategy strategy;

    File file;
    private int repDegree;
    private int amount;

    public Initiator(InitiatorStrategy strategy) {
        this.strategy = strategy;
    }

    public Initiator(File file, int repDegree, InitiatorStrategy strategy) {
        this.file = file;
        this.repDegree = repDegree;
        this.strategy = strategy;
    }

    public Initiator(int amount, InitiatorStrategy strategy) {

        this.amount = amount;
        this.strategy = strategy;

    }

    @Override
    public void run() {
        this.strategy.execute();

    }


}
