package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import ekgp49.dbc.domain.Information;
import ekgp49.dbc.service.InformationService;

public class InformationListServlet implements Servlet {
  InformationService informationService;

  public InformationListServlet(InformationService informationService) {
    this.informationService = informationService;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    List<Information> infos = null;
    try {
      infos = informationService.list();
    } catch (Exception e) {
      out.println(e.getMessage());
      e.printStackTrace();
    }
    for (Information info : infos) {
      out.printf("%d, %s, %s, %s, %s, %s ~ %s, %s\n", info.getNo(), info.getCafeName(),
          info.getCafeAddress(), info.getCafeCall(), info.getCafeWebSite(), info.getOpenTime(),
          info.getCloseTime(), info.getHolliday());
    }
  }
}
