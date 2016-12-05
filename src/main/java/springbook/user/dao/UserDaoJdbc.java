package springbook.user.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import springbook.user.domain.Level;
import springbook.user.domain.User;
import springbook.user.sqlservice.SqlService;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.util.List;


/**
 * @author Kj Nam
 * @since 2016-11-26
 */
public class UserDaoJdbc implements UserDao {
    private JdbcTemplate jdbcTemplate;
    private SqlService sqlService;

    public void setSqlService(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    private RowMapper<User> userMapper =
            (rs, rowNum) -> {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setLevel(Level.valueOf(rs.getInt("level")));
                user.setRecommend(rs.getInt("recommend"));
                user.setLogin(rs.getInt("login"));
                return user;
            };

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(User user) throws ClassNotFoundException, SQLException {
        this.jdbcTemplate.update(
                this.sqlService.getSql("userAdd"),
                user.getId(), user.getName(), user.getPassword(), user.getEmail(), user.getLevel().intValue(), user.getRecommend(), user.getLogin());
    }

    @Override
    public void update(User user) throws SQLRecoverableException {
        this.jdbcTemplate.update(
                this.sqlService.getSql("userUpdate"),
                user.getName(), user.getPassword(), user.getEmail(), user.getLevel().intValue(), user.getRecommend(), user.getLogin(), user.getId());
    }

    @Override
    public User get(String id) throws ClassNotFoundException, SQLException {
        return this.jdbcTemplate.queryForObject(
                this.sqlService.getSql("userGet"),
                new Object[]{id}, this.userMapper);
    }

    @Override
    public void deleteAll() throws SQLRecoverableException {
        this.jdbcTemplate.update(this.sqlService.getSql("userDeleteAll"));
    }

    @Override
    public int getCount() throws SQLException {
        return this.jdbcTemplate.queryForObject(
                this.sqlService.getSql("userGetCount"),
                new Object[] {}, Integer.class);
   }

    @Override
    public List<User> getAll() throws SQLRecoverableException {
        return this.jdbcTemplate.query(
                this.sqlService.getSql("userGetAll"),
                this.userMapper);
    }
}
