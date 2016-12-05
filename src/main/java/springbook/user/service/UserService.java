package springbook.user.service;

import org.springframework.transaction.annotation.Transactional;
import springbook.user.domain.User;

import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.util.List;

/**
 * @author Kj Nam
 * @since 2016-12-01
 */
@Transactional
public interface UserService {
    void upgradeLevels() throws SQLException;

    void add(User user) throws SQLException, ClassNotFoundException;

    @Transactional(readOnly = true)
    User get(String id) throws SQLException, ClassNotFoundException;

    @Transactional(readOnly = true)
    List<User> getAll() throws SQLRecoverableException;

    void deleteAll() throws SQLRecoverableException;

    void update(User user) throws SQLRecoverableException;
}
