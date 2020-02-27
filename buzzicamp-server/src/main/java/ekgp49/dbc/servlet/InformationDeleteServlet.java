package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import ekgp49.dbc.dao.InfoMenuDao;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.util.ConnectionFactory;
import ekgp49.util.PlatformTransactionManager;
import ekgp49.util.Prompt;

public class InformationDeleteServlet implements Servlet {
  InformationDao infoDao;
  InfoMenuDao infoMenuDao;
  ConnectionFactory conFactory;
  PlatformTransactionManager txManager;

  public InformationDeleteServlet(InformationDao infoDao, InfoMenuDao infoMenuDao,
      ConnectionFactory conFactory) {
    this.infoDao = infoDao;
    this.infoMenuDao = infoMenuDao;
    this.conFactory = conFactory;
    PlatformTransactionManager txManager = new PlatformTransactionManager(conFactory);
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    int no = Prompt.getInputInt(in, out, "정보 번호? ");

    txManager.beginTransaction();
    try {
      infoMenuDao.deleteAll(no);
      if (infoDao.delete(no) > 0) {
        txManager.commit();
        out.println("삭제했습니다.");
      } else {
        throw new Exception("번호가 유효하지 않습니다.");
      }
    } catch (Exception e) {
      out.println(e.getMessage());
      txManager.rollback();
    }
  }
}
