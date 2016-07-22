package springbook.user.dao;

import springbook.user.domain.User;

import java.util.List;

/**
 * @author Kj Nam
 * @since 2016-07-20
 */
public interface UserDao {
    void add(User user);
    User get(String id);
    void deleteAll();
    int getCount();
    List<User> getAll();
}
