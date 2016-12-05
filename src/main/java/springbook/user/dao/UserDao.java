package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.util.List;

/**
 * @author Kj Nam
 * @since 2016-11-30
 */
public interface UserDao {
    void add(User user) throws ClassNotFoundException, SQLException;

    User get(String id) throws ClassNotFoundException, SQLException;

    void deleteAll() throws SQLRecoverableException;

    int getCount() throws SQLException;

    List<User> getAll() throws SQLRecoverableException;

    void update(User user1) throws SQLRecoverableException;
}
