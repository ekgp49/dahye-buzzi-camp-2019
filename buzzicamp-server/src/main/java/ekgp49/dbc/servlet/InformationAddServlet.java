package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import ekgp49.dbc.dao.InfoMenuDao;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.dbc.domain.InfoMenu;
import ekgp49.dbc.domain.Information;
import ekgp49.util.ConnectionFactory;
import ekgp49.util.PlatformTransactionManager;
import ekgp49.util.Prompt;

public class InformationAddServlet implements Servlet {
  InformationDao infoDao;
  InfoMenuDao infoMenuDao;
  ConnectionFactory conFactory;
  PlatformTransactionManager txManager;

  public InformationAddServlet(InformationDao infoDao, InfoMenuDao infoMenuDao,
      ConnectionFactory conFactory) {
    this.infoDao = infoDao;
    this.infoMenuDao = infoMenuDao;
    this.conFactory = conFactory;
    txManager = new PlatformTransactionManager(conFactory);
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

    txManager.beginTransaction();
    try {
      if (infoDao.insert(info) > 0) {
        while (true) {
          InfoMenu menu = new InfoMenu();
          String name = Prompt.getInputString(in, out, "메뉴? ");
          if (name.length() == 0) {
            break;
          }
          menu.setName(name);
          menu.setInformationNo(info.getNo());
          infoMenuDao.insert(menu);
        }
        txManager.commit();
        out.println("새 정보를 저장하였습니다.");
      } else {
        throw new Exception("새 정보 저장 실패");
      }
    } catch (Exception e) {
      txManager.rollback();
      out.println(e.getMessage());
    }
  }
}
