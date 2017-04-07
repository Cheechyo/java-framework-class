package kr.ac.jejunu;

import java.sql.SQLException;

/**
 * Created by Cheechyo on 2017. 3. 15..
 */
public class UserDao {
    private JdbcContext jdbcContext;

    public User get(Long id) throws SQLException, ClassNotFoundException {
        final String sql = "select * from userinfo where id = ?";
        Object[] params = new Object[]{id};
        return jdbcContext.queryForObject(sql, params);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        final String sql = "INSERT INTO userinfo(name,password) values (?, ?)";
        Object[] params = new Object[]{user.getName(), user.getPassword()};
        return jdbcContext.insert(sql, params);
    }

    public void update(User user) {
        String sql = "UPDATE userinfo SET NAME = ?, PASSWORD = ? WHERE ID = ?";
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        jdbcContext.update(sql, params);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM userinfo where id = ?";
        Object[] params = new Object[]{id};
        jdbcContext.update(sql, params);
    }

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }
}
