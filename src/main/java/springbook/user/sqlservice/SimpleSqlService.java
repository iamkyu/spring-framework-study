package springbook.user.sqlservice;

import java.sql.SQLRecoverableException;
import java.util.Map;

/**
 * @author Kj Nam
 * @since 2016-12-05
 */
public class SimpleSqlService implements SqlService {
    private Map<String, String> sqlMap;

    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }

    @Override
    public String getSql(String key) throws SQLRecoverableException {
        String sql = sqlMap.get(key);
        if (sql == null) {
            throw new SQLRecoverableException(key + " can't find sql");
        } else {
            return sql;
        }
    }
}
