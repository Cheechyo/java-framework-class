package jr.ac.jejunu;

import java.sql.*;

/**
 * Created by Cheechyo on 2017. 3. 15..
 */
public class UserDao {
    public User get(Long id) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
        preparedStatement.setLong(1, id);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        resultSet.next();
        User user = new User();
        user.setId(resultSet.getLong("ID"));
        user.setName(resultSet.getString("NAME"));
        user.setPassword(resultSet.getString("PASSWORD"));
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO userinfo(name,password) values (?, ?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("SELECT last_insert_id()");
        preparedStatement.execute();
        final ResultSet resultSet = preparedStatement.getResultSet();
        resultSet.next();
        final long id = resultSet.getLong(1);

        preparedStatement.close();
        connection.close();
        return id;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/jeju?characterEncoding=utf-8", "jeju", "root");
    }
}
