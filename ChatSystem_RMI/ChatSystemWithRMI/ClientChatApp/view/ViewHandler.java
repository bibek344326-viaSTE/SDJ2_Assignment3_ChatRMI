package ClientChatApp.view;

import ClientChatApp.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {
    private Scene chatScene, createAccountScene, loginScene;
    private Stage stage;
    private ViewModelFactory vmf;

    public ViewHandler(ViewModelFactory vmf) {
        this.vmf = vmf;
    }

    public void start() {
        stage = new Stage();
        openLogin();
    }


    public void openChat() {
        if (chatScene == null) {
            try {
                Parent root = loadFXML("../view/chat/Chat.fxml");
                stage.setTitle("Chat");
                chatScene = new Scene(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(chatScene);
        stage.show();

    }


    public void openCreateAccount() {
        if (createAccountScene == null) {
            try {
                Parent root = loadFXML("../view/createAccount/createAccount.fxml");
                stage.setTitle("Create Account");
                createAccountScene = new Scene(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(createAccountScene);
        stage.show();
    }

    public void openLogin() {
        if (loginScene == null) {
            try {
                Parent root = loadFXML("../view/login/login.fxml");
                loginScene = new Scene(root);
                stage.setTitle("Login");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(loginScene);
        stage.show();
    }

    private Parent loadFXML(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();

        ViewController ctrl = loader.getController();
        ctrl.init(this, vmf);
        return root;
    }
}
