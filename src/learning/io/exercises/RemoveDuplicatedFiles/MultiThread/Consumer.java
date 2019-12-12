package learning.io.exercises.RemoveDuplicatedFiles.MultiThread;

import java.io.File;

public interface Consumer {

    File consume() throws InterruptedException;

    boolean isEmpty();
}
