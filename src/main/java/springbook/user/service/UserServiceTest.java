package springbook.user.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kj Nam
 * @since 2016-07-23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserServiceTest {
    @Autowired
    private ApplicationContext context;

    @Autowired
    UserService userService;

    private UserDao dao;

    List<User> users;

    @Before
    public void setUp() {
        this.dao = context.getBean("userDao", UserDao.class);

        users = Arrays.asList(
            new User("test1", "tester1", "pass1", Level.BASIC, 49, 0),
            new User("test2", "tester2", "pass2", Level.BASIC, 50, 0),
            new User("test3", "tester3", "pass3", Level.SILVER, 60, 29),
            new User("test4", "tester4", "pass4", Level.SILVER, 60, 30),
            new User("test5", "tester5", "pass5", Level.GOLD, 100, 100)
        );
    }

    @Test
    public void upgradeLevels() {
        dao.deleteAll();

        for (User user : users)
            dao.add(user);

        userService.upgradeLevels();
        checkLevel(users.get(0), Level.BASIC);
        checkLevel(users.get(1), Level.SILVER);
        checkLevel(users.get(2), Level.SILVER);
        checkLevel(users.get(3), Level.GOLD);
        checkLevel(users.get(4), Level.GOLD);
    }

    private void checkLevel(User user, Level expectedLevel) {
        User userUpgrade = dao.get(user.getId());
        assertThat(userUpgrade.getLevel(), is(expectedLevel));
    }
}
