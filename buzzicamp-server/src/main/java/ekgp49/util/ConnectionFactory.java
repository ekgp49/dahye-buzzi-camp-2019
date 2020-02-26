package ekgp49.util;

import java.sql.Connection;
import java.sql.DriverManager;
import ekgp49.sql.ConnectionProxy;

public class ConnectionFactory {

  String url;
  String user;
  String password;

  ThreadLocal<Connection> con;

  public ConnectionFactory(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
    con = new ThreadLocal<>();
  }

  public Connection getConnection() throws Exception {
    if (con.get() != null) {
      return con.get();
    }
    con.set(new ConnectionProxy(DriverManager.getConnection(url, user, password)));
    return con.get();
  }

  public Connection removeConnection() {
    Connection removedCon = con.get();
    con.remove();
    return removedCon;
  }
}

