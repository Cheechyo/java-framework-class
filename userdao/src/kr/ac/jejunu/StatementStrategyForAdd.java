package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Cheechyo on 2017. 4. 15..
 */
public class StatementStrategyForAdd implements StatementStrategy {
    @Override
    public PreparedStatement makeStatement(Object param, Connection connection) throws SQLException {
        User user = (User) param;
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO userinfo(ID, NAME, PASSWORD) values (?, ?, ?)");
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());
        return preparedStatement;
    }
}
