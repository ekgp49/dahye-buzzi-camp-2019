package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import ekgp49.dbc.domain.Review;
import ekgp49.dbc.service.ReviewService;
import ekgp49.util.Prompt;

public class ReviewAddServlet implements Servlet {
  ReviewService reviewService;

  public ReviewAddServlet(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    Review review = new Review();
    review.setCafeName(Prompt.getInputString(in, out, "상호? "));
    review.setCustomer(Prompt.getInputString(in, out, "고객명? "));
    review.setStarRate(Prompt.getInputInt(in, out, "별점? "));
    review.setContent(Prompt.getInputString(in, out, "내용? "));

    reviewService.add(review);
    out.println("새 리뷰를 저장했습니다.");
  }
}
