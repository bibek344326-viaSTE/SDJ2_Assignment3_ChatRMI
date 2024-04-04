package Server.mediator.chat;

import Server.model.ChatHandler;
import Server.model.LoginHandler;
import shared.Message;
import shared.PrivateMessage;
import shared.clientInterfaces.ClientCallBack;
import shared.serverInterfaces.ChatServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RemoteChatServer implements ChatServer {
    private List<ClientCallBack> allClients;
    private ChatHandler chatHandler;
    private LoginHandler loginHandler;

    public RemoteChatServer(ChatHandler chatHandler, LoginHandler loginHandler) throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        this.chatHandler = chatHandler;
        this.loginHandler = loginHandler;
        allClients = new ArrayList<>();
    }

    @Override
    public List<Message> getMessages() throws RemoteException {
        return chatHandler.getMessages();
    }

    @Override
    public void addMessage(Message message) throws RemoteException {
        chatHandler.addMessage(message);
        for (ClientCallBack all : allClients
        ) {
            all.updateGlobalChat(message);

        }
    }

    @Override
    public void addPrivateMessage(PrivateMessage privateMessage) throws RemoteException {
        chatHandler.addPrivateMessage(privateMessage);
        for (ClientCallBack clients : allClients
        ) {
            try {
                if (clients.getUsername().equals(privateMessage.getUsername1()) || clients.getUsername().equals(privateMessage.getUsername2())) {
                    clients.updatePrivateChat(privateMessage.getSendMessage());
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                // allClients.remove(clients);

            }
        }
    }

    @Override
    public List<Message> getPrivateMessage(PrivateMessage arg) throws RemoteException {
        return chatHandler.getPrivateMessage(arg);
    }

    @Override
    public void setAllClients(List<ClientCallBack> allClients) throws RemoteException {
        this.allClients = allClients;
    }

    @Override
    public void isDisconnected(ClientCallBack clientImplRMI) throws RemoteException {
        for (ClientCallBack client : allClients
        ) {
            client.hasBeenDisconnected(clientImplRMI.getUsername());
            loginHandler.removeActiveUser(clientImplRMI.getUser());

        }
        // send to alive clients
    }
}
