package shared;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> allUsers;

    public UserList() {
        allUsers = new ArrayList<>();
    }

    public void addUser(User user) {
        allUsers.add(user);
    }

    public void removeUser(User user) {
        allUsers.remove(user);
    }

    public ArrayList<String> allUserNames() {
        ArrayList<String> usernames = new ArrayList<>();
        for (int i = 0; i < allUsers.size(); i++) {
            usernames.add(allUsers.get(i).getUserName());
        }
        return usernames;
    }

    public boolean contains(User user) {
        for (int i = 0; i < allUsers.size(); i++) {
            if (user.equals(allUsers.get(i))){
                return true;
            }
        }
        return false;
    }
    public int size()
    {
        return allUsers.size();
    }
    public User get(int index){
        return allUsers.get(index);
    }

}
