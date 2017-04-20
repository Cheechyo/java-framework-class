package kr.ac.jejunu;

/**
 * Created by Cheechyo on 2017. 4. 20..
 */
public class DaoFactory {

    public UserDao getUserDao() {
        return new UserDao(new DConnectionMaker());
    }
}
