package Server.model;

import shared.User;
import shared.UserList;

import java.util.List;

public class LoginHandlerManager implements LoginHandler{
    private UserList everyUsers;

    public LoginHandlerManager()
    {
        everyUsers= new UserList();
    }
    @Override
    public boolean addUser(User user) {
        everyUsers.addUser(user);
//        for (int i = 0; i < everyUsers.size(); i++) {
//            System.out.println(everyUsers.get(i));
//        }
//        System.out.println(everyUsers);
        return true;
    }

    @Override
    public void removeUser(User user) {
        everyUsers.removeUser(user);
    }

    @Override
    public boolean isConnectionPossible(String userName) {
        return !(everyUsers.allUserNames().contains(userName));
    }

    @Override
    public boolean isLoginPossible(User user) {
        return everyUsers.contains(user);
    }

    @Override
    public List<String> getAllUsers() {
        return everyUsers.allUserNames();
    }

}
