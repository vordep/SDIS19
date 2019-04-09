package initiators;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class BackUpStrategy implements InitiatorStrategy {

    File file;
    int repDgree;

    public BackUpStrategy(File file, int repDgree) {
        this.file = file;
        this.repDgree = repDgree;

    }

    @Override
    public void execute() {
        try {
            byte[] data =Files.readAllBytes(file.toPath());



        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
