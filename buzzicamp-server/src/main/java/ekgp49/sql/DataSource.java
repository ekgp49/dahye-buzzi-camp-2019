package ekgp49.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

  String url;
  String user;
  String password;

  ThreadLocal<Connection> conLocal;
  List<Connection> conList;

  public DataSource(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
    conLocal = new ThreadLocal<>();
    conList = new ArrayList<>();
  }

  public Connection getConnection() throws Exception {
    Connection con = conLocal.get();
    if (con != null) {
      return con;
    }

    if (conList.size() > 0) {
      return conList.remove(0);
    } else {
      con = new ConnectionProxy(DriverManager.getConnection(url, user, password));
    }

    conLocal.set(con);
    return con;
  }

  public Connection removeConnection() {
    Connection con = conLocal.get();
    if (con != null) {
      conLocal.remove();
      conList.add(con);
    }
    return con;
  }

  public void clean() {
    while (conList.size() > 0) {
      try {
        ((ConnectionProxy) conList.remove(0)).realClose();
      } catch (Exception e) {

      }
    }
  }
}

