package ClientChatApp.mediator;

import shared.Message;
import shared.PrivateMessage;
import shared.User;
import shared.utils.Subject;

import java.util.List;

public interface Client extends Subject {

    boolean isConnectionPossible(String username);

    void sendMessage(Message message);


    List<Message> getMessages();

    List<String> getUserList();

    boolean addUser(User user);

    boolean isLoginPossible(User user);

    void sendPrivateMessage(PrivateMessage privateMessage);


    List<Message> getUsersMessage(PrivateMessage privateMessage);

    void disconnected();
}
