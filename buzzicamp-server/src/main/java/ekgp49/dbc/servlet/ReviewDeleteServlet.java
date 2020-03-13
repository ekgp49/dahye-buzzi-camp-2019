package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import ekgp49.dbc.service.ReviewService;
import ekgp49.util.Prompt;

public class ReviewDeleteServlet implements Servlet {
  ReviewService reviewService;

  public ReviewDeleteServlet(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    int no = Prompt.getInputInt(in, out, "리뷰 번호? ");
    reviewService.delete(no);
    out.println("리뷰를 삭제했습니다.");
  }
}
