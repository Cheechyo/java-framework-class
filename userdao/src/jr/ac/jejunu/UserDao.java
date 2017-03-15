package jr.ac.jejunu;

import java.sql.*;

/**
 * Created by Cheechyo on 2017. 3. 15..
 */
public class UserDao {
    public User get(Long id) throws SQLException, ClassNotFoundException {
        // User는 mysql에 있음
        // Class를 로딩해야되겠네
        Class.forName("com.mysql.jdbc.Driver");
        // 커넥션을 맺기
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jeju", "jeju", "root");
        // 쿼리를 만들어야겠네
        PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
        preparedStatement.setLong(1, id);
        // 쿼리를 실해야겠네
        preparedStatement.execute();
        // 실행된 결과를 객체에 매핑
        ResultSet resultSet = preparedStatement.getResultSet();
        resultSet.next();
        User user = new User();
        user.setId(resultSet.getLong("ID"));
        user.setName(resultSet.getString("NAME"));
        user.setPassword(resultSet.getString("PASSWORD"));
        // 자원 해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
        // 결과를 리턴
        return user;
    }
}
