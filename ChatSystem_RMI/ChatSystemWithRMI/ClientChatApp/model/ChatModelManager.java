package ClientChatApp.model;

import ClientChatApp.mediator.Client;
import shared.Message;
import shared.PrivateMessage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class ChatModelManager implements ChatModel {
    private Client client;
    private PropertyChangeSupport support;

    public ChatModelManager(Client client) {
        this.client = client;
        this.support = new PropertyChangeSupport(this);
        client.addListener("addMessage", this::messageAdded);
        client.addListener("userNameAdded", this::userNameAdded);
        client.addListener("addPrivateMessage", this::privateMessageAdded);
    }

    private void privateMessageAdded(PropertyChangeEvent event) {
        support.firePropertyChange("addPrivateMessage", null, event.getNewValue());
    }

    private void messageAdded(PropertyChangeEvent event) {
        Message message = (Message) event.getNewValue();
        support.firePropertyChange("addMessage", null, message);
    }

    private void userNameAdded(PropertyChangeEvent event) {
        String username = (String) event.getNewValue();
        support.firePropertyChange("userNameAdded", null, username);
    }


    @Override
    public void sendMessage(Message message) {
        client.sendMessage(message);
    }


    @Override
    public List<Message> getMessages() {
        return client.getMessages();
    }

    @Override
    public List<String> getUsernames() {
        return client.getUserList();
    }

    @Override
    public void sendPrivateMessage(PrivateMessage privateMessage) {
        client.sendPrivateMessage(privateMessage);
    }


    @Override
    public List<Message> getUsersMessage(String userName1, String userName2) {
        PrivateMessage privateMessage = new PrivateMessage(userName1, userName2);
        return client.getUsersMessage(privateMessage);
    }


    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {

        support.removePropertyChangeListener(eventName, listener);
    }
}
