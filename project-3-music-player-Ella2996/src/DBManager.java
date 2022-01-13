import java.sql.Connection;
import java.sql.*;

public class DBManager {
    public static void main(String[] args)
    {
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:music.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate("insert into songs values (2, 'Something', 1, 1);");
            statement.executeUpdate("insert into users values (3, 'Paul', 'PaulsPW');");
            ResultSet rs = statement.executeQuery("select * from artists where name == 'Beatles'");

            while (rs.next()) {
                System.out.println("name = " + rs.getString("name"));
                System.out.println("id = " + rs.getInt("id"));
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if (connection != null)
                    connection.close();
            }
            catch (SQLException e)
            {
                System.err.println(e.getMessage());
            }
        }
    }
}
