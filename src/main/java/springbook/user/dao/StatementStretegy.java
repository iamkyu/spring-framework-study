package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Kj Nam
 * @since 2016-11-28
 */
public interface StatementStretegy {
    PreparedStatement makeStatemnt(Connection c) throws SQLException;
}
