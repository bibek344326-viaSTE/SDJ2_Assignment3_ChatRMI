package ClientChatApp.view.chat;

import ClientChatApp.view.ViewController;
import ClientChatApp.view.ViewHandler;
import ClientChatApp.viewmodel.ChatViewModel;
import ClientChatApp.viewmodel.ViewModelFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import shared.Message;

public class ChatViewController implements ViewController {
    private ViewHandler viewHandler;
    private ChatViewModel viewModel;
    @FXML
    private TextField sendMessage;
    @FXML
    private TextField selectedItem;
    @FXML
    private ListView<Message> listView;
    @FXML
    private ListView<String> userList;
    @FXML
    private TextField sendPrivate;
    @FXML private Label userError;
    @FXML
    private ListView<Message> privateMessageList;


    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.viewHandler = vh;
        this.viewModel = vmf.getChatViewModel();
        selectedItem.textProperty().bind(viewModel.getSelectedItem());
        viewModel.loadMessages();
        listView.setItems(viewModel.getMessages());
        userList.setItems(viewModel.getUserList());
        userError.textProperty().bind(viewModel.getUserError());
        privateMessageList.setItems(viewModel.getPrivateMessages());
        //   sendMessage.textProperty().bindBidirectional(viewModel.getMessage());

    }

    @FXML
    private void onSend() {
        viewModel.sendMessage(sendMessage.getText());
        sendMessage.clear();
    }

    @FXML
    private void onSelectUser(ActionEvent event) {
        viewModel.getSelectedItem().set(userList.getSelectionModel().getSelectedItem());
        viewModel.loadUsersMessage();
    }
    @FXML
    private void onLogOut()
    {
        viewModel.logOut();
        viewHandler.openLogin();
    }
    @FXML
    private void onSendPrivateMessage(ActionEvent event){
        viewModel.sendPrivate(sendPrivate.getText());

    }

}
