package springbook.user.sqlservice;

import java.sql.SQLRecoverableException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kj Nam
 * @since 2016-12-06
 */
public class HashMapSqlRegistry implements SqlRegistry {
    private Map<String, String> sqlMap = new HashMap<>();

    @Override
    public void registerSql(String key, String sql) {
        sqlMap.put(key, sql);
    }

    @Override
    public String findSql(String key) throws SQLRecoverableException {
        String sql = sqlMap.get(key);
        if (sql == null) {
            throw new SQLRecoverableException();
        } else {
            return sql;
        }
    }
}
