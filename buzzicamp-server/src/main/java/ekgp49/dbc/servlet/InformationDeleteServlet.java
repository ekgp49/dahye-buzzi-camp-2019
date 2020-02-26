package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import ekgp49.dbc.dao.InfoMenuDao;
import ekgp49.dbc.dao.InformationDao;
import util.Prompt;

public class InformationDeleteServlet implements Servlet {
  InformationDao infoDao;
  InfoMenuDao infoMenuDao;

  public InformationDeleteServlet(InformationDao infoDao, InfoMenuDao infoMenuDao) {
    this.infoDao = infoDao;
    this.infoMenuDao = infoMenuDao;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    int no = Prompt.getInputInt(in, out, "정보 번호? ");

    infoMenuDao.deleteAll(no);
    if (infoDao.delete(no) > 0) {
      out.println("삭제했습니다.");
    } else {
      out.println("번호가 유효하지 않습니다.");
    }
  }
}
