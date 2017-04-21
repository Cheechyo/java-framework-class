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
        Object[] params = {id};
        String sql = "select * from userinfo where id = ?";
        User user = jdbcContext.query(params, sql);
        return user;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Object[] params = {user.getId(), user.getName(), user.getPassword()};
        String sql = "INSERT INTO userinfo values(?, ?, ?)";
        jdbcContext.update(params, sql);
    }

    public void delete(User user) throws SQLException {
        Object[] params = {user.getId()};
        String sql = "DELETE FROM userinfo WHERE id = ?";
        jdbcContext.update(params,sql);
    }
}
