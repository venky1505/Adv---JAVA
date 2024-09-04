import java.sql.*;

public class jdbc {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "123456");
        Statement stmt = con.createStatement();
        if (stmt != null) {
            System.out.println("hi");
        }
    }
}
