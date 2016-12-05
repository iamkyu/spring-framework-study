package springbook.user.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import springbook.user.domain.Level;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;


/**
 * @author Kj Nam
 * @since 2016-11-26
 */
public class UserDaoJdbc implements UserDao {
    private JdbcTemplate jdbcTemplate;
    private String sqlAdd;

    public void setSqlAdd(String sqlAdd) {
        this.sqlAdd = sqlAdd;
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
                this.sqlAdd,
                user.getId(), user.getName(), user.getPassword(), user.getEmail(), user.getLevel().intValue(), user.getRecommend(), user.getLogin());
    }

    @Override
    public void update(User user) {
        this.jdbcTemplate.update("update users set name=?, password=?, email=?, level=?, recommend=?, login=? where id=?",
                user.getName(), user.getPassword(), user.getEmail(), user.getLevel().intValue(), user.getRecommend(), user.getLogin(), user.getId());
    }

    @Override
    public User get(String id) throws ClassNotFoundException, SQLException {
        return this.jdbcTemplate.queryForObject("select * from users where id=?",
                new Object[]{id}, this.userMapper);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update("delete from users");
    }

    @Override
    public int getCount() throws SQLException {
        return this.jdbcTemplate.queryForObject("select count(*) from users", new Object[] {}, Integer.class);
   }

    @Override
    public List<User> getAll() {
        return this.jdbcTemplate.query("select * from users order by id", this.userMapper);
    }
}
