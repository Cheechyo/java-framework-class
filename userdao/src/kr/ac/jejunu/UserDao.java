package kr.ac.jejunu;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Cheechyo on 2017. 3. 15..
 */
public class UserDao {
    private JdbcContext jdbcContext;

    public User get(Long id) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement;
        };
        return jdbcContext.jdbcContextWithStatementStrategyForGet(statementStrategy);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO userinfo(name,password) values (?, ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            return preparedStatement;
        };
        return jdbcContext.jdbcContextWithStatementStrategyForAdd(statementStrategy);
    }

    public void update(User user) {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE userinfo SET NAME = ?, PASSWORD = ? WHERE ID = ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());
            return preparedStatement;
        };
        jdbcContext.jdbcContextForStatementStrategyForUpdate(statementStrategy);
    }

    public void delete(Long id) {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM userinfo where id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement;
        };
        jdbcContext.jdbcContextForStatementStrategyForUpdate(statementStrategy);
    }

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }
}
