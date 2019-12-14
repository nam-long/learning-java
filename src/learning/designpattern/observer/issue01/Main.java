package learning.designpattern.observer.issue01;

public class Main {

    public static void main(String[] args) {

        Shop shop = new Shop();
        shop.register(new Client("Client-01"));
        shop.register(new Client("Client-02"));
        shop.register(new Client("Client-03"));

        shop.notifyClients("I have new product!");
    }
}
