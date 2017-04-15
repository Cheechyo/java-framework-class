package kr.ac.jejunu;

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

    public User get(Long id) {
        StatementStrategy statementStrategy = new StatementStrategyForGet();
        User user = jdbcContext.jdbcContextWithStatementStrategyForGet(id, statementStrategy);
        return user;
    }

    public void add(User user) {
        StatementStrategy statementStrategy = new StatementStrategyForAdd();
        jdbcContext.jdbcContextWithStatementStrategyForAdd(user, statementStrategy);
    }

    public void delete(User user) {
        StatementStrategy statementStrategy = new StatementStrategyForDelete();
        jdbcContext.jdbcContextWithStatementStrategyForAdd(user, statementStrategy);
    }

}
