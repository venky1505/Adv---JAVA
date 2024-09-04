import java.sql.*;
class PreparedStatementDemo {
    public static void main(String args[]) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection
                    con = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "123456");
            PreparedStatement stmt = con.prepareStatement("insert into employee values(?,?,?)");
            stmt.setInt(1,7);//1 specifies the first parameter in the query
            stmt.setString(2, "BOb");
            stmt.setString(3, "class d");
            int i = stmt.executeUpdate();
            System.out.println(i + " records inserted");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}