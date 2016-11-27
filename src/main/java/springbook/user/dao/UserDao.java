package springbook.user.dao;

import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;


/**
 * @author Kj Nam
 * @since 2016-11-26
 */
public class UserDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws Exception {
        Connection c = dataSource.getConnection();

        PreparedStatement pstmt = c.prepareStatement("INSERT INTO USERS(id, name, password) VALUES(?,?,?)");
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getId());
        pstmt.setString(3, user.getId());

        pstmt.close();
        c.close();
    }
}
