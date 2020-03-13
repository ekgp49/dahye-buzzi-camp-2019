package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import ekgp49.dbc.domain.Review;
import ekgp49.dbc.service.ReviewService;

public class ReviewListServlet implements Servlet {
  ReviewService reviewService;

  public ReviewListServlet(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    List<Review> list = reviewService.list();
    out.println("리뷰");
    for (Review r : list) {
      out.printf("%d, %s, %s, %s, %s, %s \n%s\n", r.getNo(), r.getCafeName(), r.getCustomer(),
          r.getStarRate(), r.getCreatedDate(), r.getViewCount(), r.getContent());
      out.flush();
    }
  }
}
