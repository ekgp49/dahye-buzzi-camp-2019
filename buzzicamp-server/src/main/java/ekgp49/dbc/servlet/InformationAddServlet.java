package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ekgp49.dbc.domain.InfoMenu;
import ekgp49.dbc.domain.Information;
import ekgp49.dbc.service.InformationService;
import ekgp49.util.Prompt;

public class InformationAddServlet implements Servlet {

  InformationService informationService;

  public InformationAddServlet(InformationService informationService) {
    this.informationService = informationService;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    Information info = new Information();
    info.setCafeName(Prompt.getInputString(in, out, "상호? "));
    info.setCafeAddress(Prompt.getInputString(in, out, "주소? "));
    info.setCafeCall(Prompt.getInputString(in, out, "전화번호? "));
    info.setCafeWebSite(Prompt.getInputString(in, out, "웹사이트"));
    info.setOpenTime(Prompt.getInputString(in, out, "오픈시간? "));
    info.setCloseTime(Prompt.getInputString(in, out, "클로즈시간? "));
    info.setHolliday(Prompt.getInputString(in, out, "휴일? "));

    List<InfoMenu> menuList = new ArrayList<>();

    while (true) {
      InfoMenu menu = new InfoMenu();
      String name = Prompt.getInputString(in, out, "메뉴? ");
      if (name.length() == 0) {
        break;
      }
      menu.setName(name);
      menu.setInformationNo(info.getNo());
      menuList.add(menu);
    }
    info.setMenuList(menuList);

    informationService.add(info);

    out.println("새 정보를 저장하였습니다.");

  }
}
