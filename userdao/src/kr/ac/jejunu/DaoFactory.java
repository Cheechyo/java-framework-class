package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Cheechyo on 2017. 4. 21..
 */
@Configuration
public class DaoFactory {
    @Bean
    public UserDao getUserDao() {
        return new UserDao(getConnectionMaker());
    }
    @Bean
    public ConnectionMaker getConnectionMaker() {
        return new JejuConnectionMaker();
    }
}
