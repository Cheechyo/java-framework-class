package kr.ac.jejunu;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Cheechyo on 2017. 3. 15..
 */
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    public User get(Long id) {
        Object[] params = new Object[]{id};
        final String sql = "select * from userinfo where id = ?";
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

    public void add(User user) {
        Object[] params = new Object[]{
                user.getId()
                , user.getName()
                , user.getPassword()
        };
        final String sql = "INSERT INTO userinfo(ID, NAME, PASSWORD) values (?, ?, ?)";
        jdbcTemplate.update(sql, params);
    }

    public void delete(User user) {
        Object[] params = new Object[]{user.getId()};
        final String sql = "DELETE from userinfo where id = ?";
        jdbcTemplate.update(sql, params);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
