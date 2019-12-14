package learning.designpattern.strategy.issue01;

public class FlyWithWings implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I can fly");
    }
}
