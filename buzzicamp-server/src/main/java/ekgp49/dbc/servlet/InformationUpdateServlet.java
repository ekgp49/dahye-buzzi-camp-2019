package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ekgp49.dbc.domain.InfoMenu;
import ekgp49.dbc.domain.Information;
import ekgp49.dbc.service.InformationService;
import ekgp49.util.Prompt;

public class InformationUpdateServlet implements Servlet {
  InformationService informationService;

  public InformationUpdateServlet(InformationService informationService) {
    this.informationService = informationService;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    int no = Prompt.getInputInt(in, out, "번호? ");
    Information info = informationService.get(no);
    if (info == null) {
      throw new Exception("해당번호의 회원정보이 없습니다.");
    }
    info.setCafeName(Prompt.getInputString(in, out, "상호: "));
    info.setCafeAddress(Prompt.getInputString(in, out, "주소: "));
    info.setCafeCall(Prompt.getInputString(in, out, "연락처: "));
    info.setCafeWebSite(Prompt.getInputString(in, out, "웹사이트: "));
    info.setOpenTime(Prompt.getInputString(in, out, "오픈시간: "));
    info.setCloseTime(Prompt.getInputString(in, out, "클로즈시간: "));
    info.setHolliday(Prompt.getInputString(in, out, "정기 휴일: "));

    out.println("메뉴: ");

    List<InfoMenu> menuList = new ArrayList<>();
    while (true) {
      InfoMenu menu = new InfoMenu();
      String name = Prompt.getInputString(in, out, "> ");
      if (name.length() == 0) {
        break;
      }
      menu.setName(name);
      menu.setInformationNo(info.getNo());
      menuList.add(menu);
    }

    informationService.update(info, menuList);
    out.println("정보를 수정하였습니다.");
  }
}
