package Server.model;

import shared.Message;
import shared.PrivateMessage;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ChatHandlerManager implements ChatHandler{
    private List<Message> messageList;
    private List<PrivateMessage> privateMessageList;
    private PropertyChangeSupport support;


    public ChatHandlerManager() {
        privateMessageList = new ArrayList<>();
        messageList = new ArrayList<>();
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void addMessage(Message message) {
        messageList.add(message);
        //  support.firePropertyChange("addMessage",null,message);   // message OR messageList to send ??
    }

    @Override
    public void addPrivateMessage(PrivateMessage privateMessage) {
        if (doesPrivateMessageExists(privateMessage)){
            for (int i = 0; i < privateMessageList.size(); i++) {
                if (privateMessage.equals(privateMessageList.get(i))){
                    privateMessageList.get(i).addMessage(privateMessage.getSendMessage());
                    System.out.println("Message added to existing");
                }

            }
        }
        else{
            privateMessage.addMessage(privateMessage.getSendMessage());
            privateMessageList.add(privateMessage);
            System.out.println("Message added to new");
        }


    }

    @Override
    public List<Message> getPrivateMessage(PrivateMessage arg) {
        for (int i = 0; i < privateMessageList.size(); i++) {
            if (arg.equals(privateMessageList.get(i))){
                return privateMessageList.get(i).getMessageList().getMessages();
            }
        }
        return null;
    }


    private boolean doesPrivateMessageExists(PrivateMessage privateMessage) {
        for (int i = 0; i < privateMessageList.size(); i++) {
            if (privateMessageList.get(i).equals(privateMessage)){
                return true;
            }

        }
        return false;
    }

    @Override
    public List<Message> getMessages() {
        return messageList;
    }


    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
