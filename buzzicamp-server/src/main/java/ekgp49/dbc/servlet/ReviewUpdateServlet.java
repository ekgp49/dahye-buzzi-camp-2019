package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import ekgp49.dbc.domain.Review;
import ekgp49.dbc.service.ReviewService;
import ekgp49.util.Prompt;

public class ReviewUpdateServlet implements Servlet {
  ReviewService reviewService;

  public ReviewUpdateServlet(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    int no = Prompt.getInputInt(in, out, "번호? ");
    Review old = reviewService.get(no);
    if (old == null) {
      out.println("해당번호의 리뷰가 없습니다.");
      return;
    }
    Review review = new Review();
    review.setNo(old.getNo());
    review.setStarRate(Prompt.getInputInt(in, out, String.format("별점(%d)? ", old.getStarRate()),
        String.valueOf(old.getStarRate())));
    review.setContent(Prompt.getInputString(in, out, String.format("내용(%s)? ", old.getContent()),
        old.getContent()));

    if (reviewService.update(review) > 0) {
      out.println("리뷰를 변경했습니다.");
    } else {
      throw new Exception("리뷰 변경 실패");
    }
  }
}
