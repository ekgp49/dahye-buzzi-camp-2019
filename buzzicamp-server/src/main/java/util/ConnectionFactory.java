package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

  String url;
  String user;
  String password;

  public ConnectionFactory(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
  }

  public Connection getConnection() throws Exception {
    return DriverManager.getConnection(url, user, password);
  }
}


