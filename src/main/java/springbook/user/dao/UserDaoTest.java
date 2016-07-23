package springbook.user.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springbook.user.domain.Level;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserDaoTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    DataSource dataSource;

    private UserDao userDao;
    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() {
        this.userDao = context.getBean("userDao", UserDao.class);

        this.user1 = new User("test1", "tester1", "pass1", Level.BASIC, 1, 0);
        this.user2 = new User("test2", "tester2", "pass2", Level.SILVER, 55, 10);
        this.user3 = new User("test3", "tester3", "pass3", Level.GOLD, 100, 40);
    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        userDao.deleteAll();
        assertThat(userDao.getCount(), is(0));

        userDao.add(user1);
        userDao.add(user2);
        assertThat(userDao.getCount(), is(2));

        User userGet1 = userDao.get(user1.getId());
        checkSameUser(userGet1, user1);

        User userGet2 = userDao.get(user2.getId());
        assertThat(userGet2.getName(), is(user2.getName()));
        checkSameUser(userGet2, user2);
    }

    private void checkSameUser(User user1, User user2) {
        assertThat(user1.getId(), is(user2.getId()));
        assertThat(user1.getName(), is(user2.getName()));
        assertThat(user1.getPassword(), is(user2.getPassword()));
        assertThat(user1.getLevel(), is(user2.getLevel()));
        assertThat(user1.getLogin(), is(user2.getLogin()));
        assertThat(user1.getRecommend(), is(user2.getRecommend()));
    }


    @Test
    public void count() throws SQLException, ClassNotFoundException {
        userDao.deleteAll();
        assertThat(userDao.getCount(), is(0));

        userDao.add(user1);
        assertThat(userDao.getCount(), is(1));

        userDao.add(user2);
        assertThat(userDao.getCount(), is(2));

        userDao.add(user3);
        assertThat(userDao.getCount(), is(3));

        userDao.deleteAll();
        assertThat(userDao.getCount(), is(0));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException, ClassNotFoundException {
        userDao.deleteAll();
        assertThat(userDao.getCount(), is(0));

        userDao.get("unknown_id");
    }

    @Test
    public void getAll () {
        userDao.deleteAll();

        List<User> users0 = userDao.getAll();
        assertThat(users0 .size(), is(0));
    }

    @Test(expected = DataAccessException.class)
    public void duplicateKey() throws Exception {
        userDao.deleteAll();

        userDao.add(user1);
        userDao.add(user1);
    }

    @Test
    public void update() {
        userDao.deleteAll();
        userDao.add(user1);
        userDao.add(user2);

        user1.setName("new1");
        user1.setPassword("newuser1");
        user1.setLevel(Level.GOLD);
        user1.setLogin(1000);
        user1.setRecommend(999);
        userDao.update(user1);

        User user1update = userDao.get(user1.getId());
        checkSameUser(user1, user1update);
        User user2same = userDao.get(user2.getId());
        checkSameUser(user2, user2same);
    }



    /* Not Working java: <T>is(java.lang.Class<T>) in org.hamcrest.Matchers has been deprecated

    @Test
    public void sqlExceptionTranslate() {
        userDao.deleteAll();

        try {
            userDao.add(user1);
            userDao.add(user2);
        } catch (DuplicateKeyException e) {
            SQLException sqlEx = (SQLException) e.getRootCause();
            SQLExceptionTranslator set = new SQLErrorCodeSQLExceptionTranslator(this.dataSource);
            assertThat(set.translate(null, null, sqlEx), is(DuplicateKeyException.class));
        }
    }
    */

}