package ClientChatApp.mediator;

public class ClientFactory {
    private Client client;

    public Client getClient() {
        if (client == null) {
            client = new ClientSocket();
        }

        return client;
    }
}
