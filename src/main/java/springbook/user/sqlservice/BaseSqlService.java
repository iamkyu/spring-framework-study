package springbook.user.sqlservice;

import javax.annotation.PostConstruct;
import java.sql.SQLRecoverableException;

/**
 * @author Kj Nam
 * @since 2016-12-06
 */
public class BaseSqlService implements SqlService {
    protected SqlReader sqlReader;
    protected SqlRegistry sqlRegistry;

    public void setSqlReader(SqlReader sqlReader) {
        this.sqlReader = sqlReader;
    }

    public void setSqlRegistry(SqlRegistry sqlRegistry) {
        this.sqlRegistry = sqlRegistry;
    }

    @PostConstruct
    public void loadSql() {
        this.sqlReader.read(this.sqlRegistry);
    }

    @Override
    public String getSql(String key) throws SQLRecoverableException {
        return this.sqlRegistry.findSql(key);
    }
}
