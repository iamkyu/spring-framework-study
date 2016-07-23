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

    private UserDao userDao;

    List<User> users;

    @Before
    public void setUp() {
        this.userDao = context.getBean("userDao", UserDao.class);

        users = Arrays.asList(
            new User("test1", "tester1", "pass1", Level.BASIC, 49, 0),
            new User("test2", "tester2", "pass2", Level.BASIC, 50, 0),
            new User("test3", "tester3", "pass3", Level.SILVER, 60, 29),
            new User("test4", "tester4", "pass4", Level.SILVER, 60, 30),
            new User("test5", "tester5", "pass5", Level.GOLD, 100, 100)
        );
    }

    @Test
    public void add() {
        userDao.deleteAll();

        User userWithLevel = users.get(4);
        User userWithoutLevel = users.get(0);
        userWithLevel.setLevel(null);

        userService.add(userWithLevel);
        userService.add(userWithoutLevel);

        User userWithLevelRead = userDao.get(userWithLevel.getId());
        User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

        assertThat(userWithLevelRead.getLevel(), is(userWithLevel.getLevel()));
        assertThat(userWithoutLevelRead.getLevel(), is(Level.BASIC));
    }

    @Test
    public void upgradeLevels() {
        userDao.deleteAll();

        for (User user : users)
            userDao.add(user);

        userService.upgradeLevels();
        checkLevel(users.get(0), Level.BASIC);
        checkLevel(users.get(1), Level.SILVER);
        checkLevel(users.get(2), Level.SILVER);
        checkLevel(users.get(3), Level.GOLD);
        checkLevel(users.get(4), Level.GOLD);
    }

    private void checkLevel(User user, Level expectedLevel) {
        User userUpgrade = userDao.get(user.getId());
        assertThat(userUpgrade.getLevel(), is(expectedLevel));
    }
}
