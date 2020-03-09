package processor.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class SellersStorage {

  public SellersStorage() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println(e.getCause().getMessage());
    }
  }


  public Connection getConnection() {
    Connection connection = null;
    try (InputStream is =
        SellersStorage.class
            .getClassLoader()
            .getResourceAsStream("hikari.properties")) {
      Properties properties = new Properties();
      properties.load(is);
      HikariConfig config = new HikariConfig(properties);
      HikariDataSource dataSource = new HikariDataSource(config);
      connection = dataSource.getConnection();
    } catch (IOException | SQLException e) {
      System.out.println(e.getCause().getMessage());
    }

    return connection;
  }
}
