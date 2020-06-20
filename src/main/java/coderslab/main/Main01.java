package coderslab.main;

import java.sql.SQLException;
import java.util.Scanner;

public class Main01 {
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        String komenda;
        komenda = getData();
        while (!komenda.equals("Stop")) {
            switch (komenda) {
                case "Add":
                    addUser();
                    komenda = getData();
                    break;
                case "Remove_certain":
                    removeCertain();
                    komenda = getData();
                    break;
                case "List_all":
                    showAll();
                    ;
                    komenda = getData();
                    break;
                case "List_certain":
                    showCertain();
                    komenda = getData();
                    break;
                case "Update_certain":
                    updateCertain();
                    komenda = getData();
                    break;
            }

        }


    }

    public static void showAll() throws SQLException {
        for (User user : UserDao.getAll()) {
            System.out.println(user);
        }
    }

    public static void addUser() throws SQLException {
        User user = new User();
        Scanner scan = new Scanner(System.in);
        System.out.println("Wpisz email: ");
        String email = scan.nextLine();
        user.setEmail(email);
        System.out.println("Wpisz nazwę użytkownika: ");
        String username = scan.nextLine();
        user.setUsername(username);
        System.out.println("Wpisz hasło użytkownika: ");
        String password = scan.nextLine();
        user.setPassword(password);
        UserDao.createUser(user);
    }

    public static void removeCertain() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Wpisz id usera, który ma zostać usunięty: ");
        int a = scan.nextInt();
        if (UserDao.removeUser(a) == 0) {
            System.out.println("Użytkownik o takim id nie istnieje");
        } else {
            System.out.println("Użytkownik został pomyślnie usunięty");
        }
    }

    static void showCertain() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Wpisz id usera, który ma zostać wyświetlony: ");
        int a = scan.nextInt();
        if (UserDao.showUser(a) == null) {
            System.out.println("Użytkownik o takim id nie istnieje");
        } else {
            System.out.println(UserDao.showUser(a));
        }
    }

    static void updateCertain() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Wpisz id usera, którego dane ulegną zmianie: ");
        int a = scan.nextInt();
        if (UserDao.showUser(a) == null) {
            System.out.println("Użytkownik o takim id nie istnieje");
        } else {
            UserDao.updateUser(a);
        }
    }

    static String getData() {
        String dzialanie = " ";
        Scanner input = new Scanner(System.in);
        printActions();
        dzialanie = input.nextLine();
        while (!dzialanie.equals("Add") && !dzialanie.equals("Remove_certain") && !dzialanie.equals("List_all") && !dzialanie.equals("List_certain") && !dzialanie.equals("Update_certain") && !dzialanie.equals("Stop")) {
            System.out.println("Podanej komendy nie istnieje, proszę wpisz poprawną komendę: ");
            printActions();
            dzialanie = input.nextLine();
        }
        return dzialanie;
    }


    static void printActions() {
        System.out.println("Wpisz pożądaną operację: ");
        System.out.println("Add");
        System.out.println("Remove_certain");
        System.out.println("List_all");
        System.out.println("List_certain");
        System.out.println("Update_certain");
        System.out.println("Stop");
    }
}