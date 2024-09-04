import java.sql.*;
public class CallableStatementDemo {
    public static void main(String[] args) throws Exception
    {
        Class.forName("org.postgresql.Driver");
        // Getting the connection
        Connection con =
                DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres",
                        "123456");
        String sql_string = "insert into employee values(?,?,?)";
        // Preparing a CallableStatement
        CallableStatement cs = con.prepareCall(sql_string);
        cs.setInt(1, 9);
        cs.setString(2, "Sravs");
        cs.setString(3, "class e");
        cs.execute();
        System.out.print("uploaded successfully\n");
        Statement st =
                con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery("select * from employee where id=9;");
        int cnt=rs.getMetaData().getColumnCount();
        int i=1;
        System.out.println("Inserted Details are:");
        while(i<=cnt && rs.next())
        {
            System.out.println(rs.getObject(i++));
        }
    }
}