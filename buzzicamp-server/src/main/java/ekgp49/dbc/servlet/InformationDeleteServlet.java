package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import ekgp49.dbc.dao.InfoMenuDao;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.sql.PlatformTransactionManager;
import ekgp49.sql.TransactionTemplate;
import ekgp49.util.Prompt;

public class InformationDeleteServlet implements Servlet {
  InformationDao infoDao;
  InfoMenuDao infoMenuDao;
  PlatformTransactionManager txManager;
  TransactionTemplate transactionTemplate;

  public InformationDeleteServlet(InformationDao infoDao, InfoMenuDao infoMenuDao,
      PlatformTransactionManager txManager) {
    this.infoDao = infoDao;
    this.infoMenuDao = infoMenuDao;
    this.txManager = txManager;
    transactionTemplate = new TransactionTemplate(txManager);
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    int no = Prompt.getInputInt(in, out, "정보 번호? ");

    transactionTemplate.execute(() -> {
      infoMenuDao.deleteAll(no);
      if (infoDao.delete(no) > 0) {
        out.println("삭제했습니다.");
      } else {
        throw new Exception("번호가 유효하지 않습니다.");
      }
      return null;
    });
  }
}
