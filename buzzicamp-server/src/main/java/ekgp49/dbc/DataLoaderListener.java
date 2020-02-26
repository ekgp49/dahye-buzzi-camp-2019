package ekgp49.dbc;

import java.sql.DriverManager;
import java.util.Map;
import ekgp49.dbc.context.ApplicationContextListener;
import ekgp49.dbc.dao.mariadb.InfoMenuDaoImpl;
import ekgp49.dbc.dao.mariadb.InformationDaoImpl;
import ekgp49.dbc.dao.mariadb.ReviewDaoImpl;

public class DataLoaderListener implements ApplicationContextListener {
  public static java.sql.Connection con;

  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/myproject", "buzzi", "1111");
      context.put("infoDao", new InformationDaoImpl(con));
      context.put("reviewDao", new ReviewDaoImpl(con));
      context.put("infoMenuDao", new InfoMenuDaoImpl(con));
    } catch (Exception e) {
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장합니다.");
    try {
      con.close();
    } catch (Exception e) {
    }
  }
}
