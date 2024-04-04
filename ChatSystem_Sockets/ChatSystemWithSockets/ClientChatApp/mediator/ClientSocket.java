package ClientChatApp.mediator;

import shared.Message;
import shared.PrivateMessage;
import shared.User;
import shared.utils.Request;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ClientSocket implements Client{
    private PropertyChangeSupport support;
    //  private String userName;


    public ClientSocket() {
        support = new PropertyChangeSupport(this);

    }

    @Override
    public void startListeningToServer(User user) {
        try {
            Socket socket = new Socket("localhost", 6789);
            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

            Thread t = new Thread(() -> listenToServer(inFromServer, outToServer, user));
            t.setDaemon(true);
            t.start();

        } catch (IOException e) {
            e.printStackTrace(); // something like throw new RuntimeException("Cannot connect to server can be done here")
        }
    }


    @Override
    public List<Message> getMessages() {
        try {
            Request response = request(null, "getMessage");
            return (List<Message>) response.getArg();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getUserList() {

        try {
            Request response = request(null, "getUserList");
            return (List<String>) response.getArg();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addUser(User user) {
        try {
            Request response = request(user, "addUser");
            return (boolean) response.getArg();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isLoginPossible(User user) {
        try {
            Request response = request(user, "isLoginPossible");
            boolean b = (boolean) response.getArg();
            if (b) {
                startListeningToServer(user);
            }
            return b;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void sendPrivateMessage(PrivateMessage privateMessage) {
        try {
            Request response = request(privateMessage,"addPrivateMessage");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean doesPrivateMessageExists(String username1, String username2) {
        try {
            Request response = request(new String[]{username1,username2},"doesPrivateMessageExists");
            return (boolean) response.getArg();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Message> getUsersMessage(PrivateMessage privateMessage) {
        try {
            Request response = request(privateMessage,"getUsersMessage");
            return (List<Message>) response.getArg();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void listenToServer(ObjectInputStream inFromServer, ObjectOutputStream outToServer, User user) {
        try {
            outToServer.writeObject(new Request("Listener", user));
            while (true) {
                Request response = (Request) inFromServer.readObject();
                if (response.getType().equals("addMessage")) {
                    support.firePropertyChange("addMessage", null, response.getArg());
                } else if (response.getType().equals("userAdded")) {
                    support.firePropertyChange("userAdded", null, response.getArg());
                } else if (response.getType().equals("userRemoved")) {
                    support.firePropertyChange("userRemoved",null,response.getArg());
                }
                else if (response.getType().equals("addPrivateMessage")){
                    support.firePropertyChange("addPrivateMessage",null,response.getArg());
                }
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean isConnectionPossible(String username) {
        try {
            Request response = request(username, "connectionRequest");
            return (boolean) response.getArg();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void sendMessage(Message message) {
        try {
            Request response = request(message, "addMessage");
            Message newMessage = (Message) response.getArg();
             support.firePropertyChange("addMessage",null,newMessage);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Request request(Object arg, String type) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 6789);
        ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
        outToServer.writeObject(new Request(type, arg));
        return (Request) inFromServer.readObject();
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
