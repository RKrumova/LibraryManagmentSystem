import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    static Connection conn=null;
    static Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");
            conn= DriverManager.getConnection("jdbc:h2:C:\\Users\\bitchin25\\IdeaProjects\\Library_060\\Library","bitchin25","bitchin25");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
