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
        Object[] params = new Object[]{id};
        final String sql = "select * from userinfo where id = ?";
        return jdbcContext.select(params, sql);
    }

    public void add(User user) {
        Object[] params = new Object[]{
            user.getId()
            , user.getName()
            , user.getPassword()
        };
        final String sql = "INSERT INTO userinfo(ID, NAME, PASSWORD) values (?, ?, ?)";
        jdbcContext.update(params, sql);
    }

    public void delete(User user) {
        Object[] params = new Object[]{user.getId()};
        final String sql = "DELETE from userinfo where id = ?";
        jdbcContext.update(params, sql);
    }

}
