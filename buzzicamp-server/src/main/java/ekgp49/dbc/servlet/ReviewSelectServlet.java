package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.domain.Review;

public class ReviewSelectServlet implements Servlet {
  ReviewDao reviewDao;

  public ReviewSelectServlet(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    out.println("별점? \n!{}!");
    int star = Integer.parseInt(in.nextLine());
    List<Review> list = reviewDao.selectStar(star);
    for (Review r : list) {
      out.printf("%d, %s, %s, %s, %s, %s \n%s\n", r.getNo(), r.getCafeName(), r.getCustomer(),
          r.getStarRate(), r.getCreatedDate(), r.getViewCount(), r.getContent());
    }
  }
}
