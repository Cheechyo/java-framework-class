package kr.ac.jejunu;

/**
 * Created by Cheechyo on 2017. 4. 21..
 */
public class DaoFactory {
    public UserDao getUserDao() {
        return new UserDao(getConnectionMaker());
    }

    private JejuConnectionMaker getConnectionMaker() {
        return new JejuConnectionMaker();
    }
}