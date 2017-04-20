package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Cheechyo on 2017. 4. 20..
 */
public interface StatementStrategy {
    PreparedStatement makeStatement(Connection connection, Object obj) throws SQLException;
}
