package springbook.user.service;

import springbook.user.domain.User;

import java.sql.SQLException;

/**
 * @author Kj Nam
 * @since 2016-12-01
 */
public interface UserService {
    void upgradeLevels() throws SQLException;

    void add(User user) throws SQLException, ClassNotFoundException;
}
