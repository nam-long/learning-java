package learning.sample;

public class Main {

    public static void main(String[]  args) {

        String folder = "test/duplicated-files";
        String filename = "test/duplicated-files/abc.txt";

        ConsumerProducer cp = new ConsumerProducer();

        Cleaner cleaner01 = new Cleaner("Cleaner-01", filename, cp);
        cleaner01.execute();

        Scanner scanner = new Scanner("Scanner-00", folder, filename, cp);
        scanner.execute();
    }
}
