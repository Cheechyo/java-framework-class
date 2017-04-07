package kr.ac.jejunu;

import java.sql.SQLException;

/**
 * Created by Cheechyo on 2017. 3. 15..
 */
public class UserDao {
    private JdbcContext jdbcContext;

    public User get(Long id) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new GetUserStatementStrategy(id);
        return jdbcContext.jdbcContextWithStatementStrategyForGet(statementStrategy);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new AddUserStatementStrategy(user);
        return jdbcContext.jdbcContextWithStatementStrategyForAdd(statementStrategy);
    }

    public void update(User user) {
        StatementStrategy statementStrategy = new UpdateUserStatementStrategy(user);
        jdbcContext.jdbcContextForStatementStrategyForUpdate(statementStrategy);
    }

    public void delete(Long id) {
        StatementStrategy statementStrategy = new DeleteUserStatementStrategy(id);
        jdbcContext.jdbcContextForStatementStrategyForUpdate(statementStrategy);
    }

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }
}
