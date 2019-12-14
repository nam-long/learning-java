package learning.designpattern.observer.issue01;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    private List<Client> mClients = new ArrayList<>();

    public void register(Client client) {

        if (!mClients.contains(client)) {
            mClients.add(client);
        }
    }

    public void unregister(Client client) {
        mClients.remove(client);
    }

    public void notifyClients(String message) {

        for (Client client: mClients) {
            client.receive(message);
        }
    }
}
