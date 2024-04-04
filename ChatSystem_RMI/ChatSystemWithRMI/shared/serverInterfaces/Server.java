package shared.serverInterfaces;

import shared.clientInterfaces.ClientCallBack;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {
    LoginServer getLoginServer() throws RemoteException;
    ChatServer getChatServer() throws  RemoteException;
    void registerClient(ClientCallBack client) throws RemoteException;
    void startServer() throws RemoteException, AlreadyBoundException;

    void isDisconnected(ClientCallBack clientImplRMI) throws RemoteException;

}
