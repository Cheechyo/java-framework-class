package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Cheechyo on 2017. 4. 14..
 */
public interface ConnectionMaker {
    Connection getConnection() throws ClassNotFoundException, SQLException;
}