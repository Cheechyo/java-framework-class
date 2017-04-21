package kr.ac.jejunu;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

/**
 * Created by Cheechyo on 2017. 3. 15..
 */
public class UserDao {
    JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User get(Long id) throws SQLException, ClassNotFoundException {
        Object[] params = {id};
        String sql = "select * from userinfo where id = ?";
        return jdbcTemplate.query(sql, params, resultSet -> {
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setPassword(resultSet.getString("PASSWORD"));
            }
            return user;
        });
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Object[] params = {user.getId(), user.getName(), user.getPassword()};
        String sql = "INSERT INTO userinfo VALUES(?,?,?)";
        jdbcTemplate.update(sql, params);
    }

    public void delete(User user) throws SQLException {
        Object[] params = {user.getId()};
        String sql = "DELETE FROM userinfo WHERE id = ?";
        jdbcTemplate.update(sql, params);
    }
}
