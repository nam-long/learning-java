package learning.designpattern.strategy.issue01;

public class Duck {

    private FlyBehavior mFlyBehavior;

    private QuackBehavior mQuackBehavior;

    public Duck() {
    }

    public void display() {}

    public void fly() {
        mFlyBehavior.fly();
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        mFlyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
    }
}
