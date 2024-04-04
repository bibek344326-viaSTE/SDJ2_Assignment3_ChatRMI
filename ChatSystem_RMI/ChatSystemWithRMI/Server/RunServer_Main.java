package Server;

import Server.mediator.chat.RemoteChatServer;
import Server.mediator.login.RemoteLoginServer;
import Server.model.ChatHandlerManager;
import Server.model.LoginHandler;
import Server.model.LoginHandlerManager;
import shared.serverInterfaces.ChatServer;
import shared.serverInterfaces.LoginServer;
import shared.serverInterfaces.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class RunServer_Main {
    public static void main(String[] args) {
        LoginHandler loginHandler = new LoginHandlerManager();
        try {
            LoginServer loginServer = new RemoteLoginServer(loginHandler);
            ChatServer chatServer = new RemoteChatServer(new ChatHandlerManager(), loginHandler);
            Server server = new RemoteServer(loginServer, chatServer);
            server.startServer();
        } catch (RemoteException | AlreadyBoundException e) {
            throw new RuntimeException(e);
        }

    }
}
