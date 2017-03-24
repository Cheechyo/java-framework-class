package jr.ac.jejunu;

/**
 * Created by Cheechyo on 2017. 3. 24..
 */
public class DaoFactory {

    public UserDao getUserDao() {
        return new UserDao(new JejuConnectionMaker());
    }
}
