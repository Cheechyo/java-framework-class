package kr.ac.jejunu;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Cheechyo on 2017. 3. 15..
 */
public class UserDao {
    private JdbcContext jdbcContext;
    private String sql;

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
        String sql = "UPDATE userinfo SET NAME = ?, PASSWORD = ? WHERE ID = ?";
        Object[] params = new Object[] {user.getName(), user.getPassword(), user.getId()};
        update(sql, params);
    }

    private void update(String sql, Object[] params) {
        StatementStrategy statementStrategy = connection -> {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i, params[i]);
            }
            return preparedStatement;
        };
        jdbcContext.jdbcContextForStatementStrategyForUpdate(statementStrategy);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM userinfo where id = ?";
        Object[] params = new Object[]{id};
        update(sql, params);
    }

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }
}
