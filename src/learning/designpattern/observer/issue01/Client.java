package learning.designpattern.observer.issue01;

public class Client {

    private String name;

    public Client(String name) {
        this.name = name;
    }

    public void receive(String message) {
        System.out.println(name + ": " + message);
    }
}
