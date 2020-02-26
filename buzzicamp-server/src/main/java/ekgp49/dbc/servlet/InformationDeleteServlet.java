package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.sql.Connection;
import java.util.Scanner;
import ekgp49.dbc.dao.InfoMenuDao;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.util.ConnectionFactory;
import ekgp49.util.Prompt;

public class InformationDeleteServlet implements Servlet {
  InformationDao infoDao;
  InfoMenuDao infoMenuDao;
  ConnectionFactory conFactory;

  public InformationDeleteServlet(InformationDao infoDao, InfoMenuDao infoMenuDao,
      ConnectionFactory conFactory) {
    this.infoDao = infoDao;
    this.infoMenuDao = infoMenuDao;
    this.conFactory = conFactory;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    int no = Prompt.getInputInt(in, out, "정보 번호? ");

    Connection con = conFactory.getConnection();
    con.setAutoCommit(false);
    try {
      infoMenuDao.deleteAll(no);
      if (infoDao.delete(no) > 0) {
        con.commit();
        out.println("삭제했습니다.");
      } else {
        throw new Exception("번호가 유효하지 않습니다.");
      }
    } catch (Exception e) {
      out.println(e.getMessage());
      con.rollback();
    } finally {
      con.setAutoCommit(true);
    }
  }
}
