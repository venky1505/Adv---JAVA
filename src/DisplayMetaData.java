import java.sql.*;

public class DisplayMetaData {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM employee"; // Modify this query

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    ResultSetMetaData metaData = resultSet.getMetaData();

                    int columnCount = metaData.getColumnCount();
                    System.out.println("Columns in the result set:");

                    for (int i = 1; i <= columnCount; i++) {
                        System.out.println("Column Name: " + metaData.getColumnName(i));
                        System.out.println("Data Type: " + metaData.getColumnTypeName(i));
                        System.out.println("Nullable: " + metaData.isNullable(i));
                        // You can retrieve more metadata if needed
                        System.out.println("-------------------------");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}