package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Cheechyo on 2017. 4. 15..
 */
public class StatementStrategyForGet implements StatementStrategy {
    @Override
    public PreparedStatement makeStatement(Object param, Connection connection) throws SQLException {
        Long id = (Long) param;
        PreparedStatement statement = connection.prepareStatement("select * from userinfo where id = ?");
        statement.setLong(1, id);
        return statement;
    }
}
