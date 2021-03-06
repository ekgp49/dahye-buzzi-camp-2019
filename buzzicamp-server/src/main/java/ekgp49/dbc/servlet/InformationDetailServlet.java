package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import ekgp49.dbc.domain.InfoMenu;
import ekgp49.dbc.domain.Information;
import ekgp49.dbc.service.InformationService;
import ekgp49.util.Prompt;

public class InformationDetailServlet implements Servlet {
  InformationService informationService;

  public InformationDetailServlet(InformationService informationService) {
    this.informationService = informationService;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    int no = Prompt.getInputInt(in, out, "정보번호? ");
    try {
      Information info = informationService.get(no);
      if (info == null) {
        throw new Exception("정보번호가 유효하지 않습니다.");
      }
      out.printf("%d, %s, %s, %s, %s, %s ~ %s, %s\n", info.getNo(), info.getCafeName(),
          info.getCafeAddress(), info.getCafeCall(), info.getCafeWebSite(), info.getOpenTime(),
          info.getCloseTime(), info.getHolliday());

      out.println("[메뉴]");
      String menu = "";
      for (InfoMenu infoMenu : info.getMenuList()) {
        menu += ", " + infoMenu.getName();
      }
      out.println(menu);
    } catch (Exception e) {
      out.println(e.getMessage());
    }
  }
}
