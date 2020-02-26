package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import ekgp49.dbc.dao.ReviewDao;
import ekgp49.util.Prompt;

public class ReviewDeleteServlet implements Servlet {
  ReviewDao reviewDao;

  public ReviewDeleteServlet(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    int no = Prompt.getInputInt(in, out, "리뷰 번호? ");
    if (reviewDao.findByNo(no) == null) {
      out.println("해당번호의 리뷰정보가 없습니다.");
    } else {
      reviewDao.delete(no);
      out.println("리뷰를 삭제했습니다.");
      out.flush();
    }
  }
}
