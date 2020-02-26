package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.domain.Review;
import util.Prompt;

public class ReviewRateServlet implements Servlet {
  ReviewDao reviewDao;

  public ReviewRateServlet(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    int star = Prompt.getInputInt(in, out, "별점? ");
    List<Review> list = reviewDao.selectStar(star);
    for (Review r : list) {
      out.printf("%d, %s, %s, %s, %s, %s \n%s\n", r.getNo(), r.getCafeName(), r.getCustomer(),
          r.getStarRate(), r.getCreatedDate(), r.getViewCount(), r.getContent());
      out.flush();
    }
  }
}
