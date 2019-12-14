package learning.designpattern.strategy.issue02;

public class Main {

    public static void main(String[] args) {

        FileReader fileReader = new FileReader();

        fileReader.setReaderBehavior(new PDFReaderBehavior());
        fileReader.readFile("abc.pdf");

        fileReader.setReaderBehavior(new ExcelReaderBehavior());
        fileReader.readFile("123.xls");
    }
}
