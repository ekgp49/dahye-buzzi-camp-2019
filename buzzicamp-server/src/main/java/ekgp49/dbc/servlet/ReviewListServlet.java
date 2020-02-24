package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.domain.Review;

public class ReviewListServlet implements Servlet {
  ReviewDao reviewDao;

  public ReviewListServlet(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    List<Review> list = reviewDao.findAll();
    out.println("리뷰");
    for (Review r : list) {
      out.printf("%d, %s, %s, %s, %s, %s \n%s\n", r.getNo(), r.getCafeName(), r.getCustomer(),
          r.getStarRate(), r.getCreatedDate(), r.getViewCount(), r.getContent());
    }
  }
}
