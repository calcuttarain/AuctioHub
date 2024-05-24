package view.app;

import user.AuthentificationService;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        AuthentificationService a = AuthentificationService.getInstance();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceți username-ul: ");
        String username = scanner.nextLine();
        System.out.print("Introduceți adresa de email: ");
        String email = scanner.nextLine();
        System.out.print("Introduceți parola: ");
        String parola = scanner.nextLine();

        a.createUser(username, email, parola);

        a.show();
    }
}