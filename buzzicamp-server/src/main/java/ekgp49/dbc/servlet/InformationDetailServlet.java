package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import ekgp49.dbc.dao.InfoMenuDao;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.dbc.domain.InfoMenu;
import ekgp49.dbc.domain.Information;
import util.Prompt;

public class InformationDetailServlet implements Servlet {
  InformationDao infoDao;
  InfoMenuDao infoMenuDao;

  public InformationDetailServlet(InformationDao infoDao, InfoMenuDao infoMenuDao) {
    this.infoDao = infoDao;
    this.infoMenuDao = infoMenuDao;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    int no = Prompt.getInputInt(in, out, "번호? ");
    Information info = infoDao.findByNo(no);
    out.printf("%d, %s, %s, %s, %s, %s ~ %s, %s\n", info.getNo(), info.getCafeName(),
        info.getCafeAddress(), info.getCafeCall(), info.getCafeWebSite(), info.getOpenTime(),
        info.getCloseTime(), info.getHolliday());

    List<InfoMenu> menu = infoMenuDao.findAll(no);
    out.println("메뉴: ");
    for (InfoMenu item : menu) {
      out.println(item.getName());
    }
  }
}