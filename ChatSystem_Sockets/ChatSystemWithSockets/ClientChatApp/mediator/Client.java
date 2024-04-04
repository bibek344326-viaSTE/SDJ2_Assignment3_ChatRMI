package ClientChatApp.mediator;

import shared.Message;
import shared.PrivateMessage;
import shared.User;
import shared.utils.Subject;

import java.util.List;

public interface Client extends Subject {

    boolean isConnectionPossible(String username);

    void sendMessage(Message message);
    void startListeningToServer(User user);
    List<Message> getMessages();

    List<String> getUserList();

    boolean addUser(User user);

    boolean isLoginPossible(User user);

    void sendPrivateMessage(PrivateMessage privateMessage);

    boolean doesPrivateMessageExists(String username1, String username2);

    List<Message> getUsersMessage(PrivateMessage privateMessage);

}
