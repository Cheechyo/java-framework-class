package kr.ac.jejunu;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Cheechyo on 2017. 4. 15..
 */
public class JdbcContext {
    private SimpleDriverDataSource dataSource;

    public void setDataSource(SimpleDriverDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public SimpleDriverDataSource getDataSource() {
        return dataSource;
    }

    public void jdbcContextWithStatementStrategyForAdd(StatementStrategy statementStrategy) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public User jdbcContextWithStatementStrategyForGet(StatementStrategy statementStrategy) {
        ResultSet resultSet = null;
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setPassword(resultSet.getString("PASSWORD"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return user;
    }

    public User select(Object[] params, String sql) {
        StatementStrategy statementStrategy = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++)
                preparedStatement.setObject(i+1, params[i]);
            return preparedStatement;
        };
        return this.jdbcContextWithStatementStrategyForGet(statementStrategy);
    }

    public void update(Object[] params, String sql) {
        StatementStrategy statementStrategy = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++)
                preparedStatement.setObject(i+1, params[i]);
            return preparedStatement;
        };
        this.jdbcContextWithStatementStrategyForAdd(statementStrategy);
    }
}
