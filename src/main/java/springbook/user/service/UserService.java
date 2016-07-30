package springbook.user.service;

import springbook.user.domain.User;

/**
 * @author Kj Nam
 * @since 2016-07-30
 */
public interface UserService {
    void upgradeLevels() throws Exception;

    void add(User user);
}
