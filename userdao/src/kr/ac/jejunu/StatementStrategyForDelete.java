package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Cheechyo on 2017. 4. 15..
 */
public class StatementStrategyForDelete implements StatementStrategy {
    @Override
    public PreparedStatement makeStatement(Object param, Connection connection) throws SQLException {
        User user = (User) param;
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE from userinfo where id = ?");
        preparedStatement.setLong(1, user.getId());
        return preparedStatement;
    }
}
