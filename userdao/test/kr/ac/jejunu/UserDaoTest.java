package kr.ac.jejunu;

import org.junit.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Cheechyo on 2017. 3. 15..
 */
public class UserDaoTest {
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String name = "Won Ji";
        String password = "Nu Ri";
        UserDao userDao = new DUserDao();
        User user = userDao.get(id);

        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        Long id = generateRandomId();
        String name = "Cheechyo";
        String password = "cheese";
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        UserDao userDao = new DUserDao();
        userDao.add(user);
        user = userDao.get(id);

        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }

    @Test
    public void nGet() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String name = "Won Ji";
        String password = "Nu Ri";
        UserDao userDao = new NUserDao();
        User user = userDao.get(id);

        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }

    @Test
    public void nAdd() throws SQLException, ClassNotFoundException {
        Long id = generateRandomId();
        String name = "Cheechyo";
        String password = "cheese";
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        UserDao userDao = new NUserDao();
        userDao.add(user);
        user = userDao.get(id);

        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }

    private Long generateRandomId() {
        Random random = new Random();
        Long l = (long) random.nextInt(Integer.MAX_VALUE);
        return l;
    }
}
