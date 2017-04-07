package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Cheechyo on 2017. 3. 15..
 */
public class UserDaoTest {
    private UserDao userDao;

    @Before
    public void setup() {
//        daoFactory = new DaoFactory();
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//        userDao = context.getBean(UserDao.class);
        ApplicationContext context = new GenericXmlApplicationContext("daoFactory.xml");
        userDao = context.getBean("userDao", UserDao.class);
        // userDao = (UserDao) context.getBean("userDao");
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String name = "Won Ji";
        String password = "Nu Ri";

        User user = userDao.get(id);
        // id == user.getId()인지 물어보는 함수
        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "Cheechyo";
        String password = "cheesse";
        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);
        User resultUser = userDao.get(id);
        assertThat(id, is(resultUser.getId()));
        assertThat(name, is(resultUser.getName()));
        assertThat(password, is(resultUser.getPassword()));
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "Cheechyo";
        String password = "cheesse";
        user.setName(name);
        user.setPassword(password);

        Long id = userDao.add(user);

        String changedName = "Cheechyo1";
        String changedPassword = "cheesse12";
        user.setId(id);
        user.setName(changedName);
        user.setPassword(changedPassword);

        userDao.update(user);
        User changedUser = userDao.get(id);
        assertThat(id, is(changedUser.getId()));
        assertThat(changedName, is(changedUser.getName()));
        assertThat(changedPassword, is(changedUser.getPassword()));
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "wjko";
        String password = "9211";
        user.setName(name);
        user.setPassword(password);

        Long id = userDao.add(user);
        userDao.delete(id);
        User deletedUser = userDao.get(id);
        assertThat(deletedUser, nullValue());
    }
}
