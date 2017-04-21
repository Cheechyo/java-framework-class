package kr.ac.jejunu;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
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
        User user = jdbcTemplate.query(sql, params, new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                User user1 = null;
                if (resultSet.next()){
                    user1 = new User();
                    user1.setId(resultSet.getLong("ID"));
                    user1.setName(resultSet.getString("NAME"));
                    user1.setPassword(resultSet.getString("PASSWORD"));
                }
                return user1;
            }
        });
        return user;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Object[] params = {user.getId(), user.getName(), user.getPassword()};
        String sql = "INSERT INTO userinfo values(?, ?, ?)";
        jdbcTemplate.update(sql, params);
    }

    public void delete(User user) throws SQLException {
        Object[] params = {user.getId()};
        String sql = "DELETE FROM userinfo WHERE id = ?";
        jdbcTemplate.update(sql, params);
    }
}
