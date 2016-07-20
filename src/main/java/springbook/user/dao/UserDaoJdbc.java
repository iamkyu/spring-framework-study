package springbook.user.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJdbc implements UserDao {
    private RowMapper<User> userMapper = new RowMapper<User>() {
        public User mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            return user;
        }

    };
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(final User user) {
        this.jdbcTemplate.update(
                "insert into users(id, name, password) values(?,?,?)",
                user.getId(), user.getName(), user.getPassword());

    }

    @Override
    public User get(String id) {
        return this.jdbcTemplate.queryForObject(
                "select * from users where id = ?", new Object[] { id },
                userMapper);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update("delete from users");
    }

    // Both queryForInt() and queryForLong() are deprecated since version 3.2.2
    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForObject("select count(*) from users", new Object[] {}, Integer.class);
    }

    @Override
    public List<User> getAll() {
        return this.jdbcTemplate.query("select * from users order by id",
                userMapper);
    }
}