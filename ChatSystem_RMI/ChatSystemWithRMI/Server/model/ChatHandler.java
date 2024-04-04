package Server.model;

import shared.Message;
import shared.PrivateMessage;
import shared.utils.Subject;

import java.util.List;

public interface ChatHandler extends Subject {
    List<Message> getMessages();
    void addMessage(Message message);

    void addPrivateMessage(PrivateMessage privateMessage);

    List<Message> getPrivateMessage(PrivateMessage arg);

}
