package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Cheechyo on 2017. 3. 24..
 */
@Configuration
public class DaoFactory {
    @Bean
    public UserDao getUserDao() {
        return new UserDao();
    }
    @Bean
    public ConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }
}
