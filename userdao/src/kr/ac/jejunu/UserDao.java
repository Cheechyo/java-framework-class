package kr.ac.jejunu;

import java.sql.PreparedStatement;
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
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
            preparedStatement.setLong(1, id);
            return  preparedStatement;
        };
        return jdbcContext.query(statementStrategy);
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO userinfo VALUES(?,?,?)");
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPassword());
            return  preparedStatement;
        };
        jdbcContext.update(statementStrategy);
    }

    public void delete(User user) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM userinfo WHERE id = ?");
            preparedStatement.setLong(1, user.getId());
            return  preparedStatement;
        };
        jdbcContext.update(statementStrategy);
    }
}
