package kr.ac.jejunu;

import java.sql.*;

/**
 * Created by Cheechyo on 2017. 3. 15..
 */
public class UserDao {

    private ConnectionMaker connectionMaker;

    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public User get(Long id) throws SQLException, ClassNotFoundException {
        Connection connection = connectionMaker.getConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            StatementStrategy statementStrategy = new GetUserStatementStrategy();
            preparedStatement = statementStrategy.makeStatement(id, connection);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            if (resultSet.next()){
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

    public Long add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        long id = 0;
        try {
            connection = connectionMaker.getConnection();
            StatementStrategy statementStrategy = new AddUserStatementStrategy();
            preparedStatement = statementStrategy.makeStatement(user, connection);
            preparedStatement.executeUpdate();

            // last_insert_id는 connection 기반. 다른 connection이 add한다 해도 그걸 가져오진 않음.
            preparedStatement = connection.prepareStatement("SELECT last_insert_id()");
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            resultSet.next();
            id = resultSet.getLong(1);
        } catch (ClassNotFoundException | SQLException e) {
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
        return id;
    }


    public long update(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        long id = 0;
        try {
            connection = connectionMaker.getConnection();
            StatementStrategy statementStrategy = new UpdateUserStatementStrategy();
            preparedStatement = statementStrategy.makeStatement(user.getId(), connection);

            preparedStatement = connection.prepareStatement("SELECT last_insert_id()");
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            resultSet.next();
            id = resultSet.getLong(1);
        } catch (ClassNotFoundException | SQLException e) {
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
        return id;
    }

    public void delete(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionMaker.getConnection();
            StatementStrategy statementStrategy = new DeleteUserStatementStrategy();
            preparedStatement = statementStrategy.makeStatement(id, connection);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
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

}
