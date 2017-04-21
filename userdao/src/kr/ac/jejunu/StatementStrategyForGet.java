package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Cheechyo on 2017. 4. 21..
 */
public class StatementStrategyForGet implements StatementStrategy {
    @Override
    public PreparedStatement makeStatement(Connection connection, Object obj) throws SQLException {
        Long id = (long) obj;
        PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
        preparedStatement.setLong(1, id);
        return preparedStatement;
    }
}
