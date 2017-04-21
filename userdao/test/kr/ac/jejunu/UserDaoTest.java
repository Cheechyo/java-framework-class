package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Cheechyo on 2017. 3. 15..
 */
public class UserDaoTest {

    private DaoFactory daoFactory;

    @Before
    public void setup(){
        daoFactory = new DaoFactory();
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String name = "Won Ji";
        String password = "Nu Ri";
        UserDao userDao = daoFactory.getUserDao();
        User user = userDao.get(id);
        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        Long id = gernarateRandomId();
        String name = "Won Ji";
        String password = "Nu Ri";
        UserDao userDao = daoFactory.getUserDao();
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        userDao.add(user);
        user = userDao.get(id);
        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }

    private Long gernarateRandomId() {
        Random random = new Random();
        return new Long(random.nextInt(Integer.MAX_VALUE));
    }
}
