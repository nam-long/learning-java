package learning.designpattern.strategy.issue01;

public class Main {

    public static void main(String[] args) {

        Duck mallardDuck = new MallardDuck();
        mallardDuck.setFlyBehavior(new FlyWithWings());
        mallardDuck.display();
        mallardDuck.fly();

        Duck rubberDuck = new RubberDuck();
        rubberDuck.setFlyBehavior(new FlyNoWay());
        rubberDuck.display();
        rubberDuck.fly();
    }
}
