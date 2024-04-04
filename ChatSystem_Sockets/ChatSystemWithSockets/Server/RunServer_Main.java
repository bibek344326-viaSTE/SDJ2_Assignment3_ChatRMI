package Server;

import Server.mediator.SocketServer;
import Server.model.ChatHandlerManager;
import Server.model.LoginHandlerManager;

public class RunServer_Main {
    public static void main(String[] args) {
        SocketServer socketServer = new SocketServer(new ChatHandlerManager(),new LoginHandlerManager());
        socketServer.startServer();
    }
}
