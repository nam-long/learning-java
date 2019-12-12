package learning.io.exercises.RemoveDuplicatedFiles.MultiThread;

import java.io.File;

public interface Producer {

    void produce(File f) throws InterruptedException;
}
