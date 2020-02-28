package ekgp49.dbc;

import java.util.Map;
import ekgp49.dbc.context.ApplicationContextListener;
import ekgp49.dbc.dao.mariadb.InfoMenuDaoImpl;
import ekgp49.dbc.dao.mariadb.InformationDaoImpl;
import ekgp49.dbc.dao.mariadb.ReviewDaoImpl;
import ekgp49.sql.DataSource;

public class DataLoaderListener implements ApplicationContextListener {
  DataSource dataSource;

  @Override
  public void contextInitialized(Map<String, Object> context) {
    String url = "jdbc:mariadb://localhost:3306/myproject";
    String user = "buzzi";
    String password = "1111";
    dataSource = new DataSource(url, user, password);

    try {
      context.put("infoDao", new InformationDaoImpl(dataSource));
      context.put("reviewDao", new ReviewDaoImpl(dataSource));
      context.put("infoMenuDao", new InfoMenuDaoImpl(dataSource));
      context.put("dataSource", dataSource);
    } catch (Exception e) {
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장합니다.");
    dataSource.clean();
  }
}
