package worker;

import java.sql.ResultSet;

/**
 * Created by alta0816 on 20.02.2017.
 */
public interface BDWorker {
    public void connect(String login, String password);

    public void createDatabase(String name);

    public void deleteDatabase(String name);

    public void cleanTable(String name);

    public void insert(String table, String object);

    public void update(String table, String object, String id);

    public ResultSet find(String table, String field, String value);

    public void deleteByFieldValue(String table, String field, String value);

    public void deleteById(String table, String id);
}
