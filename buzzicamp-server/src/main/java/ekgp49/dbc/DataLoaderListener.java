package ekgp49.dbc;

import java.util.Map;
import ekgp49.dbc.context.ApplicationContextListener;
import ekgp49.dbc.dao.mariadb.InfoMenuDaoImpl;
import ekgp49.dbc.dao.mariadb.InformationDaoImpl;
import ekgp49.dbc.dao.mariadb.ReviewDaoImpl;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    String url = "jdbc:mariadb://localhost:3306/myproject";
    String user = "buzzi";
    String password = "1111";

    try {
      context.put("infoDao", new InformationDaoImpl(url, user, password));
      context.put("reviewDao", new ReviewDaoImpl(url, user, password));
      context.put("infoMenuDao", new InfoMenuDaoImpl(url, user, password));
    } catch (Exception e) {
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장합니다.");
  }
}
