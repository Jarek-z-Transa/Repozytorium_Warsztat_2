package coderslab.main;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class UserDao {

    public static List<User> getAll() throws SQLException {
        LinkedList<User> list = new LinkedList<User>();
        Connection conn = DbUtil.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");
        while (rs.next()) {
            list.add(readOne(rs));
        }
        conn.close();
        return list;
    }

    public static void createUser(User user) throws SQLException {
        Connection conn = DbUtil.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (email, username, password) VALUES (?, ?, ?)");
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            conn.close();
        }
        catch (SQLException ex) {
            System.out.println("Nie można dodać tego użytkownika, ponieważ użytkownik z podanym email już istnieje");
        }
    }

    public static int removeUser(int abc) throws SQLException {
        Connection conn = DbUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE id = ?");
        stmt.setInt(1, abc);
        return stmt.executeUpdate();
    }

    public static User showUser(int cba) throws SQLException {
        Connection conn = DbUtil.getConnection();
        User user = new User();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        stmt.setInt(1, cba);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        } else {
            user = readOne(rs);
        }
        return user;
    }

    public static void updateUser(int a) throws SQLException {
        Connection conn = DbUtil.getConnection();
        User user = new User();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        stmt.setInt(1, a);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        user = readOne(rs);
        System.out.println("Wpisz który z atrybutów ma zostać zmieniony: ");
        Scanner scan = new Scanner(System.in);
        String komenda = scan.nextLine();
        while (!komenda.equals("email")&&!komenda.equals("username")&&!komenda.equals("password")) {
            System.out.println("Atrybutu o podanej nazwie nie istnieje");
            komenda = scan.nextLine();
        }
        switch (komenda) {
            case "email":
                System.out.println("Wprowadź nowy email: ");
                String email = scan.nextLine();
                try {
                PreparedStatement stat = conn.prepareStatement("UPDATE users SET email= ? WHERE id = ?");
                stat.setString(1, email);
                stat.setInt(2, a);
                stat.executeUpdate();
                }
                catch (SQLException ex) {
                    System.out.println("Nie można zmienić email danego użytkownika, ponieważ użytkownik o podanym email już istnieje");
                }
                break;
            case "username":
                System.out.println("Wprowadź nową nazwę użytkownika: ");
                String username = scan.nextLine();
                PreparedStatement stat2 = conn.prepareStatement("UPDATE users SET username= ? WHERE id = ?");
                stat2.setString(1, username);
                stat2.setInt(2, a);
                stat2.executeUpdate();
                break;
            case "password":
                System.out.println("Wprowadź nowe hasło użytkownika: ");
                String password = scan.nextLine();
                PreparedStatement stat3 = conn.prepareStatement("UPDATE users SET password= ? WHERE id = ?");
                stat3.setString(1, password);
                stat3.setInt(2, a);
                stat3.executeUpdate();
                break;
        }
    }

    public static User readOne(ResultSet bass) throws SQLException {
        User user = new User();
        user.setId(bass.getInt("id"));
        user.setEmail(bass.getString("email"));
        user.setUsername(bass.getString("username"));
        user.setPassword(bass.getString("password"));
        return user;
    }

}

