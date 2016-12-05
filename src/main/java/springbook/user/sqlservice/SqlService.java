package springbook.user.sqlservice;

import java.sql.SQLRecoverableException;

/**
 * @author Kj Nam
 * @since 2016-12-05
 */
public interface SqlService {
    String getSql(String key) throws SQLRecoverableException;
}
