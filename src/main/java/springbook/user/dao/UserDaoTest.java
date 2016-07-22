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

    private UserDao dao;
    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() {
        this.dao = context.getBean("userDao", UserDao.class);

        this.user1 = new User("test1", "tester1", "pass1", Level.BASIC, 1, 0);
        this.user2 = new User("test2", "tester2", "pass2", Level.SILVER, 55, 10);
        this.user3 = new User("test3", "tester3", "pass3", Level.GOLD, 100, 40);
    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User userGet1 = dao.get(user1.getId());
        checkSameUser(userGet1, user1);

        User userGet2 = dao.get(user2.getId());
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
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.get("unknown_id");
    }

    @Test
    public void getAll () {
        dao.deleteAll();

        List<User> users0 = dao.getAll();
        assertThat(users0 .size(), is(0));
    }

    @Test(expected = DataAccessException.class)
    public void duplicateKey() throws Exception {
        dao.deleteAll();

        dao.add(user1);
        dao.add(user1);
    }

    @Test
    public void update() {
        dao.deleteAll();
        dao.add(user1);

        user1.setName("new1");
        user1.setPassword("newuser1");
        user1.setLevel(Level.GOLD);
        user1.setLogin(1000);
        user1.setRecommend(999);
        dao.update(user1);

        User user1update = dao.get(user1.getId());
        checkSameUser(user1, user1update);
    }



    /* Not Working java: <T>is(java.lang.Class<T>) in org.hamcrest.Matchers has been deprecated

    @Test
    public void sqlExceptionTranslate() {
        dao.deleteAll();

        try {
            dao.add(user1);
            dao.add(user2);
        } catch (DuplicateKeyException e) {
            SQLException sqlEx = (SQLException) e.getRootCause();
            SQLExceptionTranslator set = new SQLErrorCodeSQLExceptionTranslator(this.dataSource);
            assertThat(set.translate(null, null, sqlEx), is(DuplicateKeyException.class));
        }
    }
    */

}