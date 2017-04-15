package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
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
    public void add() {
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
    public void get() {
        Long id = 1L;
        String name = "Won Ji";
        String password = "Nu Ri";
        User user = userDao.get(id);

        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        Long id = generateRandomLong();
        String name = "Hahaha";
        String password = "heheheh";
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        userDao.add(user);
        userDao.delete(user);
        assertThat(userDao.get(id), is(nullValue()));
    }

    private Long generateRandomLong() {
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        int i = r.nextInt(1000000);
        return new Long(i);
    }

}
