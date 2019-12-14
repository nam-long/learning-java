package learning.designpattern.strategy.issue01;

public class Duck {

    private String name;

    public Duck() {
    }

    public void display() {}

    public void quack() {
        System.out.println("Quack, Quack...");
    }

    public void fly() {
        System.out.println("Flying.");
    }
}
