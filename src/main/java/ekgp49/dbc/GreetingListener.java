package ekgp49.dbc;

import java.util.Map;
import ekgp49.dbc.context.ApplicationContextListener;

public class GreetingListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("어플리케이션 실행합니다.");
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("어플리케이션 종료합니다");
  }

}
