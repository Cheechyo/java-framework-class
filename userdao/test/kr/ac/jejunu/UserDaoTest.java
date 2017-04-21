package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

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
        ApplicationContext context = new GenericXmlApplicationContext("spring-context.xml");
        userDao = context.getBean(UserDao.class);
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

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        Long id = randomGenerateId();
        String name = "Cheechyo";
        String password = "cheese";
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

    private Long randomGenerateId() {
        Random random = new Random();
        Long l = new Long(random.nextInt(Integer.MAX_VALUE));
        return l;
    }
}
