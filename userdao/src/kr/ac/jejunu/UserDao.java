package kr.ac.jejunu;

import java.sql.SQLException;

/**
 * Created by Cheechyo on 2017. 3. 15..
 */
public class UserDao {
    JdbcContext jdbcContext;

    public JdbcContext getJdbcContext() {
        return jdbcContext;
    }

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Long id) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new GetUserStatementStrategy();
        return jdbcContext.jdbcContextWithStatementStrategyForGet(statementStrategy, id);
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new AddUserStatementStrategy();
        jdbcContext.jdbcContextWithStatementStrategyForDelete(statementStrategy, user);
    }

    public void delete(User user) throws SQLException {
        StatementStrategy statementStrategy = new DeleteUserStatementStrategy();
        jdbcContext.jdbcContextWithStatementStrategyForDelete(statementStrategy, user);
    }
}