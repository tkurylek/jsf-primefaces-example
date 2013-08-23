package pl.kurylek.jsf.factory;

import pl.kurylek.jsf.domain.Address;
import pl.kurylek.jsf.domain.Client;
import pl.kurylek.jsf.domain.Country;

public class ClientFactory {

    public static Client createClient() {
        Client client = new Client();
        client.setAddress(createEmptyAddress());
        return client;
    }
    
    private static Address createEmptyAddress() {
        Address emptyAddress = new Address();
        emptyAddress.setCountry(createEmptyCountry());
        return emptyAddress;
    }
    
    private static Country createEmptyCountry() {
        return new Country();
    }
}