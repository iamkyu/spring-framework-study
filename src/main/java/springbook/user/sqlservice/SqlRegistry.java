package springbook.user.sqlservice;

import java.sql.SQLRecoverableException;

/**
 * @author Kj Nam
 * @since 2016-12-06
 */
public interface SqlRegistry {
    void registerSql(String key, String sql);
    String findSql(String key) throws SQLRecoverableException;
}
