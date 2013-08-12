package pl.kurylek.jsf.factory;

import pl.kurylek.jsf.domain.Address;
import pl.kurylek.jsf.domain.Client;

public class ClientFactory {

    public static Client createClient() {
        Client client = new Client();
        client.setAddress(new Address());
        return client;
    }
}