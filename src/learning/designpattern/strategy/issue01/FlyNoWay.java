package learning.designpattern.strategy.issue01;

public class FlyNoWay implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I cannot fly");
    }
}
