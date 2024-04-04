package Server;

import ClientChatApp.mediator.clientInterfaces.ClientCallBack;
import Server.mediator.serverInterfaces.ChatServer;
import Server.mediator.serverInterfaces.LoginServer;
import Server.mediator.serverInterfaces.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RemoteServer implements Server {
    private LoginServer loginServer;
    private ChatServer chatServer;
    private List<ClientCallBack> allClients;

    public RemoteServer(LoginServer loginServer, ChatServer chatServer) throws AlreadyBoundException, RemoteException {
        this.loginServer = loginServer;
        this.chatServer = chatServer;
        UnicastRemoteObject.exportObject(this, 0);
        allClients = new ArrayList<>();

    }

    @Override
    public LoginServer getLoginServer() throws RemoteException {
        return loginServer;
    }

    @Override
    public ChatServer getChatServer() throws RemoteException {
        return chatServer;
    }

    @Override
    public void registerClient(ClientCallBack client) throws RemoteException {
        allClients.add(client);
        sendClientTOLoginServer(allClients);
        sendClientTOChatServer(allClients);
        System.out.println("A client is added");
    }

    @Override
    public void startServer() throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("Server", this);
        System.out.println("Server started...");
    }

    @Override
    public void isDisconnected(ClientCallBack clientCallBack) throws RemoteException {
        allClients.remove(clientCallBack);
        sendClientTOChatServer(this.allClients);
        chatServer.isDisconnected(clientCallBack);
    }

    private void sendClientTOLoginServer(List<ClientCallBack> allClients) throws RemoteException {
        loginServer.setAllClients(allClients);
    }

    private void sendClientTOChatServer(List<ClientCallBack> allClients) throws RemoteException {
        chatServer.setAllClients(allClients);
    }
}
