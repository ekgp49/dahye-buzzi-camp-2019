package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.dbc.domain.Information;
import util.Prompt;

public class InformationSearchServlet implements Servlet {
  InformationDao infoDao;

  public InformationSearchServlet(InformationDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    List<Information> infos = null;
    String keyword = Prompt.getInputString(in, out, "키워드? ");
    infos = infoDao.search(keyword);
    for (Information info : infos) {
      out.printf("%d, %s, %s, %s, %s, %s ~ %s, %s\n", info.getNo(), info.getCafeName(),
          info.getCafeAddress(), info.getCafeCall(), info.getCafeWebSite(), info.getOpenTime(),
          info.getCloseTime(), info.getHolliday());
    }
  }
}
