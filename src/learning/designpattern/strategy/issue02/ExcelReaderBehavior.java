package learning.designpattern.strategy.issue02;

public class ExcelReaderBehavior implements ReaderBehavior {
    @Override
    public void readFile(String filename) {
        System.out.println("Here is a Excel file: " + filename);
    }
}
