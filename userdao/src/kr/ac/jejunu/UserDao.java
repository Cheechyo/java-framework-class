package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public User get(Long id) {
        StatementStrategy statementStrategy = new StatementStrategy() {
            @Override
            public PreparedStatement makeStatement(Object param, Connection connection) throws SQLException {
                Long id = (Long) param;
                PreparedStatement statement = connection.prepareStatement("select * from userinfo where id = ?");
                statement.setLong(1, id);
                return statement;
            }
        };
        User user = jdbcContext.jdbcContextWithStatementStrategyForGet(id, statementStrategy);
        return user;
    }

    public void add(User user) {
        StatementStrategy statementStrategy = new StatementStrategy() {
            @Override
            public PreparedStatement makeStatement(Object param, Connection connection) throws SQLException {
                User user = (User) param;
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO userinfo(ID, NAME, PASSWORD) values (?, ?, ?)");
                preparedStatement.setLong(1, user.getId());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getPassword());
                return preparedStatement;
            }
        };
        jdbcContext.jdbcContextWithStatementStrategyForAdd(user, statementStrategy);
    }

    public void delete(User user) {
        StatementStrategy statementStrategy = new StatementStrategy() {
            @Override
            public PreparedStatement makeStatement(Object param, Connection connection) throws SQLException {
                User user = (User) param;
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE from userinfo where id = ?");
                preparedStatement.setLong(1, user.getId());
                return preparedStatement;

            }
        };
        jdbcContext.jdbcContextWithStatementStrategyForAdd(user, statementStrategy);
    }

}
