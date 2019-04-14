package initiators;

import chunk.Chunk;
import communication.CommadType;
import communication.Command;
import communication.CommandFactory;

import java.io.File;

public class DeleteStrategy implements InitiatorStrategy {

    public File fileToDelete;
    public   DeleteStrategy(File fileToDelete) {
        this.fileToDelete = fileToDelete;
    }

    @Override
    public void execute() {


        Command m = CommandFactory.getMessage(CommadType.DELETE);
        m.executeMessage(new Chunk(fileToDelete.getName()));

    }
}
