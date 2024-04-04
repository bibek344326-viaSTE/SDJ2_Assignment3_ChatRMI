package ClientChatApp;

import ClientChatApp.mediator.ClientFactory;
import ClientChatApp.model.ModelFactory;
import ClientChatApp.view.ViewHandler;
import ClientChatApp.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class ChatApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ClientFactory cf = new ClientFactory();
        ModelFactory mf = new ModelFactory(cf);
        ViewModelFactory vmf= new ViewModelFactory(mf);
        ViewHandler mv = new ViewHandler(vmf);
        mv.start();
    }
}
