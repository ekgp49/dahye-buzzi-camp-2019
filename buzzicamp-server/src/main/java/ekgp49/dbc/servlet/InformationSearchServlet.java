package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import ekgp49.dbc.domain.InfoMenu;
import ekgp49.dbc.domain.Information;
import ekgp49.dbc.service.InformationService;
import ekgp49.util.Prompt;

public class InformationSearchServlet implements Servlet {
  InformationService informationService;

  public InformationSearchServlet(InformationService informationService) {
    this.informationService = informationService;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    List<Information> infos = null;
    String keyword = Prompt.getInputString(in, out, "키워드? ");
    HashMap<String, Object> params = new HashMap<>();
    params.put("keyword", keyword);
    infos = informationService.search(params);
    for (Information info : infos) {
      out.printf("%d, %s, %s, %s, %s, %s ~ %s, %s\n", info.getNo(), info.getCafeName(),
          info.getCafeAddress(), info.getCafeCall(), info.getCafeWebSite(), info.getOpenTime(),
          info.getCloseTime(), info.getHolliday());
      out.println("[메뉴]");
      String menu = "";
      for (InfoMenu infoMenu : info.getMenuList()) {
        menu += infoMenu.getName() + " ";
      }
      out.println(menu);
    }
  }
}
