package kr.ac.jejunu;

/**
 * Created by Cheechyo on 2017. 4. 21..
 */
public class DaoFactory {
    public UserDao getUserDao() {
        return new UserDao(getConnectionMaker());
    }

    public ConnectionMaker getConnectionMaker() {
        return new JejuConnectionMaker();
    }
}
