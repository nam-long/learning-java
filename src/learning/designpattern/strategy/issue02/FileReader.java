package learning.designpattern.strategy.issue02;

public class FileReader {

    private ReaderBehavior mReaderBehavior;

    public void setReaderBehavior(ReaderBehavior behavior) {
        mReaderBehavior = behavior;
    }

    public void readFile(String filename) {
        mReaderBehavior.readFile(filename);
    }
}
