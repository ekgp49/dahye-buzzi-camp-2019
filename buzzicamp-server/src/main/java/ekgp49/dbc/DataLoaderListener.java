package ekgp49.dbc;

import java.util.Map;
import ekgp49.dbc.context.ApplicationContextListener;
import ekgp49.dbc.dao.mariadb.InfoMenuDaoImpl;
import ekgp49.dbc.dao.mariadb.InformationDaoImpl;
import ekgp49.dbc.dao.mariadb.ReviewDaoImpl;
import ekgp49.util.ConnectionFactory;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    String url = "jdbc:mariadb://localhost:3306/myproject";
    String user = "buzzi";
    String password = "1111";
    ConnectionFactory conFactory = new ConnectionFactory(url, user, password);

    try {
      context.put("infoDao", new InformationDaoImpl(conFactory));
      context.put("reviewDao", new ReviewDaoImpl(conFactory));
      context.put("infoMenuDao", new InfoMenuDaoImpl(conFactory));
      context.put("conFactory", conFactory);
    } catch (Exception e) {
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장합니다.");
  }
}
