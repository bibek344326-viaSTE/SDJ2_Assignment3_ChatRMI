package ClientChatApp.model;

import shared.Message;
import shared.PrivateMessage;
import shared.utils.Subject;

import java.util.List;

public interface ChatModel extends Subject {
    void sendMessage(Message message);

    List<Message> getMessages();

    List<String> getUsernames();

    void sendPrivateMessage(PrivateMessage privateMessage);

    List<Message> getUsersMessage(String userName1, String username2);

}
