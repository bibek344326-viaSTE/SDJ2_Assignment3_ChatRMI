package Server.mediator.login;

import Server.model.LoginHandler;
import shared.User;
import ClientChatApp.mediator.clientInterfaces.ClientCallBack;
import Server.mediator.serverInterfaces.LoginServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RemoteLoginServer implements LoginServer {
    private List<ClientCallBack> allClients;

    private LoginHandler loginHandler;

    public RemoteLoginServer(LoginHandler loginHandler) throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        this.loginHandler = loginHandler;
    }

    @Override
    public boolean addUser(User user) throws RemoteException {
        return loginHandler.addUser(user);
    }

    @Override
    public boolean isConnectionPossible(String userName) throws RemoteException {
        return loginHandler.isConnectionPossible(userName);
    }

    @Override
    public boolean isLoginPossible(User arg) throws RemoteException {
        if (loginHandler.isLoginPossible(arg)) {
            updateUserAdded(arg.getUserName());
            return true;
        }
        return false;
    }

    private void updateUserAdded(String username) {
        for (ClientCallBack client : allClients
        ) {
            try {
                client.updateUserAdded(username);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public List<String> getAllUsers() throws RemoteException {
        return loginHandler.getAllUsers();
    }

    @Override
    public void setAllClients(List<ClientCallBack> allClients) throws RemoteException {
        this.allClients = allClients;
    }
}
