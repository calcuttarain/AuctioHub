//package user;
//
//import business.models.Bidder;
//import business.models.User;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Collections;
//
//public class UserService {
//    private static UserService instance;
//    private List<User> users;
//
//    private UserService() {
//        this.users = new ArrayList<>(); //altfel cand o sa am un DB
//    }
//
//    static UserService getInstance() {
//        if (instance == null) {
//            instance = new UserService();
//        }
//        return instance;
//    }
//
//    void addUser(User user, int index) {
//        //cand inserez un user, vreau ca lista de useri sa ramana sortata dupa username alfabetic
//        if (index < 0) {
//            index = -(index + 1);
//        }
//        users.add(index, user);
//    }
//
//    int getUserByUsername(String username)
//    {
//        User aux = new Bidder(username);
//        return Collections.binarySearch(users, aux);
//
//    }
//
//    User getUserByIndex(int index)
//    {
//        return users.get(index);
//    }
//
//    void updateUser(User user) {
//        //changePassword cand o sa am bd
//    }
//
//    void deleteUser(String username) {
//        //cand o sa am DB
//    }
//
//    void afisare() {
//        for (User user : users) {
//            System.out.println(user);
//        }
//    }
//}
//
