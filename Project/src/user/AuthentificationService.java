//package user;
//
//import business.models.Bidder;
//import business.models.User;
//
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
//public class AuthentificationService {
//    private static AuthentificationService instance;
//    private UserService userService;
//
//    private AuthentificationService() {
//        this.userService = UserService.getInstance();
//    }
//
//    public static AuthentificationService getInstance() {
//        if (instance == null) {
//            instance = new AuthentificationService();
//        }
//        return instance;
//    }
//
//    public boolean createUser(String username, String email, String password) {
//        // Verificăm dacă username-ul există deja
//        int index = userService.getUserByUsername(username);
//        if (index > -1) {
//            System.out.println("Username already exists.");
//            return false;
//        }
//
//        // Verificăm dacă email-ul este valid
//        if (!isValidEmail(email)) {
//            System.out.println("Invalid email format.");
//            return false;
//        }
//
//        // Hashuim parola
//        String hashedPassword = hashPassword(password);
//
//        // Creăm și adăugăm utilizatorul în lista de utilizatori
//        User newBidder = new Bidder(username, email, hashedPassword);
//        userService.addUser(newBidder, index);
//
//        System.out.println("User created successfully.");
//        return true;
//    }
//
//    public boolean login(String username, String password) {
//        // Obținem indexul utilizatorului în listă
//        int index = userService.getUserByUsername(username);
//
//        // Verificăm dacă utilizatorul există
//        if (index < 0) {
//            System.out.println("Invalid username.");
//            return false;
//        }
//
//        // Obținem utilizatorul din listă
//        User user = userService.getUserByIndex(index);
//
////        if (user instanceof Bidder) {
////            Bidder current_user = (Bidder) user;
////        } else if (user instanceof Administrator) {
////            Administrator current_user = (Administrator) user;
////        } else if (user instanceof Manager) {
////            Manager current_user = (Manager) user;
////        }
//
//        if (checkPassword(password, user.getHashed_password())) {
//            System.out.println("Login successful.");
//            return true;
//        } else {
//            System.out.println("Invalid password.");
//            return false;
//        }
//    }
//
//    private boolean isValidEmail(String email) {
//        String regex = ".+@.+\\..+";
//        return email.matches(regex);
//    }
//
//    private String hashPassword(String password) {
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//
//            byte[] hashedBytes = digest.digest(password.getBytes());
//
//            StringBuilder hexString = new StringBuilder();
//            for (byte b : hashedBytes) {
//                String hex = Integer.toHexString(0xff & b);
//                if (hex.length() == 1) {
//                    hexString.append('0');
//                }
//                hexString.append(hex);
//            }
//
//            return hexString.toString();
//        } catch (NoSuchAlgorithmException e) {
//            System.err.println("Error hashing password: " + e.getMessage());
//            return password;
//        }
//    }
//
//    private boolean checkPassword(String password, String hashedPassword) {
//        String hashedInputPassword = hashPassword(password);
//        return hashedInputPassword.equals(hashedPassword);
//    }
//    public void show()
//    {
//        this.userService.afisare();
//    }
//}
//
