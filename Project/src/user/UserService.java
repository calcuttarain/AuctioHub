package user;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class UserService {
    private static UserService instance;
    private List<User> users;

    private UserService() {
        this.users = new ArrayList<>(); //altfel cand o sa am un DB
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void addUser(User user, int index) {
        //cand inserez un user, vreau ca lista de useri sa ramana sortata dupa username alfabetic
        if (index < 0) {
            index = -(index + 1);
        }
        users.add(index, user);
    }

    public int getUserByUsername(String username)
    {
        User aux = new Bidder(username);
        return Collections.binarySearch(users, aux);

    }

    public User getUserByIndex(int index)
    {
        return users.get(index);
    }

    public void updateUser(User user) {
        //changePassword cand o sa am bd
    }

    public void deleteUser(String username) {
        //cand o sa am DB
    }

    public void afisare() {
        for (User user : users) {
            System.out.println(user);
        }
    }
}

