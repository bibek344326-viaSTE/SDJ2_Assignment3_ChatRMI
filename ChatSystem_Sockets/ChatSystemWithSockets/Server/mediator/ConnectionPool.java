package Server.mediator;

import Server.model.Logger;
import shared.Message;
import shared.PrivateMessage;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private List<ServerHandler> connections = new ArrayList<>();
    private Logger logger = Logger.getInstance();

    public void addConnection(ServerHandler serverHandler) {
        connections.add(serverHandler);
        logger.log(serverHandler.getUserName(),serverHandler.getIpAddress());
    }

    public void broadcastToAll(Message message) {
        for (ServerHandler socketH : connections
        ) {
            socketH.sendMessageToClient(message);
        }
    }

    public void removeConnection(ServerHandler serverHandler) {
        connections.remove(serverHandler);
        logger.log(serverHandler.getUserName(),serverHandler.getIpAddress());
    }


    public void broadCastPrivateMessage(PrivateMessage privateMessage) {
        for (ServerHandler socketH : connections
        ) {
            if (socketH.getUserName().equals(privateMessage.getUsername1()) || socketH.getUserName().equals(privateMessage.getUsername2())) {
                socketH.sendPrivateMessageToClient(privateMessage.getSendMessage());
            }
        }
    }

    public void broadCastUsername(String userName) {
        for (ServerHandler socketH : connections
        ) {
            socketH.sendUsersToClient(userName);

        }
    }

    public void broadcastUserDisconnected(String username) {
        for (ServerHandler socketH : connections
        ) {
            socketH.sendRemovedUserToClient(username);

        }
    }

    public int size() {
        return connections.size();
    }

}
