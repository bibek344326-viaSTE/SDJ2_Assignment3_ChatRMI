package shared;

import java.util.ArrayList;
import java.util.List;

public class MessageList {
    private List<Message> messages;

    public MessageList() {
        this.messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
    public int size()
    {
        return messages.size();
    }
    public Message get(int index){
        return messages.get(index);
    }
    public List<Message> getMessages(){
        return messages;
    }

}
