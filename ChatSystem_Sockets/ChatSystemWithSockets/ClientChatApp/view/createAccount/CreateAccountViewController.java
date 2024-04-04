package ClientChatApp.view.createAccount;

import ClientChatApp.view.ViewController;
import ClientChatApp.view.ViewHandler;
import ClientChatApp.viewmodel.CreateAccountViewModel;
import ClientChatApp.viewmodel.ViewModelFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.management.ValueExp;

public class CreateAccountViewController implements ViewController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField confirm;
    @FXML
    private Label errorLabel;

    private CreateAccountViewModel viewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ViewModelFactory vmf) {
        this.viewHandler = viewHandler;
        this.viewModel = vmf.getCreateViewModel();
        username.textProperty().bindBidirectional(viewModel.getUsername());
        password.textProperty().bindBidirectional(viewModel.getPassword());
        confirm.textProperty().bindBidirectional(viewModel.getConfirm());
        errorLabel.textProperty().bind(viewModel.getError());
    }

    @FXML
    private void onAlreadyAccount(ActionEvent event) {
        viewHandler.openLogin();
    }



    @FXML
    private void onSignUp() {
        viewModel.onSignup(username.getText(), password.getText(), confirm.getText());

    }

}

