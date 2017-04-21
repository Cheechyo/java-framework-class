package kr.ac.jejunu;

import java.sql.SQLException;

/**
 * Created by Cheechyo on 2017. 3. 15..
 */
public class UserDao {
    public JdbcContext getJdbcContext() {
        return jdbcContext;
    }

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    JdbcContext jdbcContext;
    public User get(Long id) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new StatementStrategyForGet();
        User user = jdbcContext.jdbcContextWithStatementStrategyForGet(statementStrategy, id);
        return user;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new StatementStrategyForAdd();
        jdbcContext.jdbcContextWithStatementStrategyForAdd(statementStrategy, user);
    }

    public void delete(User user) throws SQLException {
        StatementStrategy statementStrategy = new StatementStrategyForDelete();
       jdbcContext.jdbcContextWithStatementStrategyForAdd(statementStrategy, user);
    }
}
