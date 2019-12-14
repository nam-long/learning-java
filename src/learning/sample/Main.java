package learning.sample;

public class Main {

    public static void main(String[]  args) {

        String folder = "test/duplicated-files";
        String filename = "test/duplicated-files/abc.txt";

        ConsumerProducer cp = new ConsumerProducer();

        Cleaner cleaner01 = new Cleaner("Cleaner-01", filename, cp);
        cleaner01.execute();

        Cleaner cleaner02 = new Cleaner("Cleaner-02", filename, cp);
        cleaner02.execute();

        Cleaner cleaner03 = new Cleaner("Cleaner-03", filename, cp);
        cleaner03.execute();

        Scanner scanner = new Scanner("Scanner-00", folder, filename, cp);
        scanner.execute();
    }
}
