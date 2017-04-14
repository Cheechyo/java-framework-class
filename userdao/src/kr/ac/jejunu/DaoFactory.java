package kr.ac.jejunu;

/**
 * Created by Cheechyo on 2017. 4. 14..
 */
public class DaoFactory {
    public UserDao getUserDao() {
        return new UserDao(new JejuConnectionMaker());
    }
}
