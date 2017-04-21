package kr.ac.jejunu;

import java.sql.*;

/**
 * Created by Cheechyo on 2017. 3. 15..
 */
public abstract class UserDao {
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

    abstract Connection getConnection() throws ClassNotFoundException, SQLException;
//    {
//        Class.forName("com.mysql.jdbc.Driver");
//        return DriverManager.getConnection("jdbc:mysql://localhost/jeju", "jeju", "root");
//    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO userinfo VALUES(?,?,?)");
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }
}
