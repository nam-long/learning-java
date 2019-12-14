package learning.designpattern.strategy.issue02;

public class PDFReaderBehavior implements ReaderBehavior {

    @Override
    public void readFile(String filename) {
        System.out.println("Here is a PDF file: " + filename);
    }
}
