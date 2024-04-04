package ClientChatApp.view.login;

import ClientChatApp.view.ViewController;
import ClientChatApp.view.ViewHandler;
import ClientChatApp.viewmodel.LoginViewModel;
import ClientChatApp.viewmodel.ViewModelFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginViewController implements ViewController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label errorLabel;

    private LoginViewModel viewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ViewModelFactory vmf) {
        this.viewHandler = viewHandler;
        this.viewModel = vmf.getLoginViewModel();
        username.textProperty().bindBidirectional(viewModel.getUsername());
        password.textProperty().bindBidirectional(viewModel.getPassword());
        errorLabel.textProperty().bind(viewModel.getError());
    }

    @FXML
    public void onCreateAccount(ActionEvent event) {
        viewHandler.openCreateAccount();
    }



    @FXML
    private void onLogin(ActionEvent event) {
        if (viewModel.onLogin(username.getText(), password.getText())) {
            viewHandler.openChat();
        }
    }


}
