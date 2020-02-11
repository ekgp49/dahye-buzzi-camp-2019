package ekgp49.dbc;

import java.util.Map;
import ekgp49.dbc.context.ApplicationContextListener;
import ekgp49.dbc.dao.json.InformationJsonFileDao;
import ekgp49.dbc.dao.json.ReviewJsonFileDao;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    context.put("infoDao", new InformationJsonFileDao("./information.json"));
    context.put("reviewDao", new ReviewJsonFileDao("./review.json"));
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {

    System.out.println("데이터를 저장합니다.");
  }
}
