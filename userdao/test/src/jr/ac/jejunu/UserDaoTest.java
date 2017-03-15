package jr.ac.jejunu;

import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by Cheechyo on 2017. 3. 15..
 */
public class UserDaoTest {
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String name = "Won Ji";
        String password = "Nu Ri";
        UserDao userDao = new UserDao();
        User user = userDao.get(id);
        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }
}
