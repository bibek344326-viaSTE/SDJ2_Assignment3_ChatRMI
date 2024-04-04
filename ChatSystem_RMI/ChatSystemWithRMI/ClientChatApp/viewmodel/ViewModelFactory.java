package ClientChatApp.viewmodel;

import ClientChatApp.model.ModelFactory;

public class ViewModelFactory {
    private final ModelFactory mf;
    public CreateAccountViewModel createViewModel;

    private ChatViewModel chatViewModel;
    private LoginViewModel loginViewModel;


    public ViewModelFactory(ModelFactory mf) {
        this.mf = mf;
    }



    public ChatViewModel getChatViewModel() {
        if (chatViewModel == null) {
            chatViewModel = new ChatViewModel(mf);
        }
        return chatViewModel;
    }

    public CreateAccountViewModel getCreateViewModel() {
        if (createViewModel == null) {
            createViewModel = new CreateAccountViewModel(mf);
        }
        return createViewModel;
    }

    public LoginViewModel getLoginViewModel() {
        if (loginViewModel == null) {
            loginViewModel = new LoginViewModel(mf);
        }
        return loginViewModel;
    }

}
