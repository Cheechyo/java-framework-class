package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Cheechyo on 2017. 3. 15..
 */
public class UserDaoTest {

    private UserDao userDao;

    @Before
    public void setup(){
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = context.getBean(UserDao.class);
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        Long id = generateRandomLong();
        String name = "Cheechyo";
        String password = "cheese";
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        userDao.add(user);
        User addedUser = userDao.get(id);
        assertThat(name, is(addedUser.getName()));
        assertThat(password, is(addedUser.getPassword()));
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String name = "Won Ji";
        String password = "Nu Ri";
        User user = userDao.get(id);

        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }

    private Long generateRandomLong() {
        Long l = new Long(new Random().nextInt());
        return l * l % 100000;
    }

}
