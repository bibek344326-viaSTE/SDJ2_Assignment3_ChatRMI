package shared;

import java.io.Serializable;

public class PrivateMessage implements Serializable {
    private String username1;
    private String username2;
    private MessageList messageList;
    private Message sendMessage;

    public PrivateMessage(String username1, String username2) {
        this.username1 = username1;
        this.username2 = username2;
        messageList = new MessageList();
        this.sendMessage=null;
    }
    public PrivateMessage(String username1, String username2, Message sendMessage){
        this.username2=username2;
        this.username1=username1;
        this.sendMessage=sendMessage;
        messageList= new MessageList();
    }

    public Message getSendMessage() {
        return sendMessage;
    }

    public void addMessage(Message message) {
        messageList.addMessage(message);
    }

    public MessageList getMessageList() {
        return messageList;
    }

    public String getUsername2() {
        return username2;
    }

    public String getUsername1() {
        return username1;
    }


    public boolean equals(Object obj){
        if (!(obj instanceof PrivateMessage)){
            return false;
        }
        PrivateMessage temp = (PrivateMessage) obj;

        if (temp.username1.equals(username1)){
            if (temp.username2.equals(username2)){
                return true;
            }
        }
        if (temp.username1.equals(username2)){
            if (temp.username2.equals(username1)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "PrivateMessage{" +
                "username1='" + username1 + '\'' +
                ", username2='" + username2 + '\'' +
                ", messageList=" + messageList;
    }
}
