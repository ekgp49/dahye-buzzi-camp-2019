package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import ekgp49.dbc.dao.InformationDao;

public class InformationDeleteServlet implements Servlet {
  InformationDao infoDao;

  public InformationDeleteServlet(InformationDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    out.println("번호? \n!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    if (infoDao.delete(no) > 0) {
      out.println("삭제했습니다.");
    } else {
      out.println("번호가 유효하지 않습니다.");
    }
  }
}
