package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.dbc.domain.Information;

public class InformationAddServlet implements Servlet {
  InformationDao infoDao;

  public InformationAddServlet(InformationDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    Information info = new Information();
    out.println("상호? \n!{}!");
    info.setCafeName(in.nextLine());
    out.println("주소?? \n!{}!");
    info.setCafeAddress(in.nextLine());
    out.println("전화번호? \n!{}!");
    info.setCafeCall(in.nextLine());
    out.println("웹사이트? \n!{}!");
    info.setCafeWebSite(in.nextLine());
    out.println("오픈시간? \n!{}!");
    info.setOpenTime(in.nextLine());
    out.println("클로즈시간? \n!{}!");
    info.setCloseTime(in.nextLine());
    out.println("휴일? \n!{}!");
    info.setHolliday(in.nextLine());
    out.println("메뉴? \n!{}!");
    info.setCafeMenu(in.nextLine());

    if (infoDao.insert(info) > 0) {
      out.println("새 정보를 저장하였습니다.");
    } else {
      out.println("새정보 저장 실패");
    }
  }
}
