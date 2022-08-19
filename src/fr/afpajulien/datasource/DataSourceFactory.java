package fr.afpajulien.datasource;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataSourceFactory {

    private DataSourceFactory() {
    }

    public static DataSource getMySQLDatSource() {
        Properties props = new Properties();
        MysqlDataSource msDS = null;

        try (FileInputStream fis = new FileInputStream(String.valueOf(Main.class.getResource("/properties/db.properties").getPath()))){
            props.load(fis);
            msDS = new MysqlDataSource();
            msDS.setURL(props.getProperty("MYSQL_DB_URL"));
            msDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
            msDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
        } catch (IOException e){
            e.printStackTrace();
        }
        return msDS;
    }
}
