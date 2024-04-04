package Server.mediator;

import Server.model.ChatHandler;
import Server.model.Logger;
import Server.model.LoginHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    private ChatHandler model;
    private LoginHandler loginHandler;


    public SocketServer(ChatHandler model, LoginHandler loginHandler) {
        this.model = model;
        this.loginHandler = loginHandler;

    }

    public void startServer() {
        try {
            ServerSocket welcomeSocket = new ServerSocket(6789);
            System.out.println("Server started....");
            ConnectionPool cp = new ConnectionPool();
            while (true) {
                System.out.println("Waiting for clients.....");
                Socket socket = welcomeSocket.accept();
                ServerHandler serverHandler = new ServerHandler(socket, model, loginHandler, cp);
//                Logger logger = Logger.getInstance();
//                logger.log(serverHandler.getUserName(), socket.getInetAddress().getHostAddress());
                System.out.println(cp.size());
                Thread thread = new Thread(serverHandler);
                thread.start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
