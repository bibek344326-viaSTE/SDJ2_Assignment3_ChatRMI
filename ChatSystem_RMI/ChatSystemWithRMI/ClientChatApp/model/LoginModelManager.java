package ClientChatApp.model;

import ClientChatApp.mediator.Client;
import shared.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginModelManager implements LoginModel{
    private Client client;
    private User user;
    private PropertyChangeSupport support;

    public LoginModelManager(Client client) {
        this.client = client;
        support = new PropertyChangeSupport(this);
        client.addListener("userAdded",this::userAdded);
        client.addListener("userRemoved",this::userRemoved);
    }

    private void userRemoved(PropertyChangeEvent event) {
        support.firePropertyChange("userRemoved",null,event.getNewValue());
    }

    private void userAdded(PropertyChangeEvent event) {
        support.firePropertyChange("userAdded",null,event.getNewValue());
    }

    @Override
    public boolean isConnectionPossible(String username) {
        return client.isConnectionPossible(username);
    }

    @Override
    public boolean addUser(String username, String password) {
        User tempUser = new User(username, password);
        return client.addUser(tempUser);

    }

    @Override
    public boolean isLoginPossible(User user) {
        boolean loginPossible = client.isLoginPossible(user);
        if (loginPossible) this.user = user;
        return loginPossible;

    }

    @Override
    public User getUser() {
        return user;
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
