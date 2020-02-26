package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.domain.Review;
import ekgp49.util.Prompt;

public class ReviewAddServlet implements Servlet {
  ReviewDao reviewDao;

  public ReviewAddServlet(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    Review review = new Review();
    review.setCafeName(Prompt.getInputString(in, out, "상호? "));
    review.setCustomer(Prompt.getInputString(in, out, "고객명? "));
    review.setStarRate(Prompt.getInputInt(in, out, "별점? "));
    review.setContent(Prompt.getInputString(in, out, "내용? "));

    if (reviewDao.insert(review) > 0) {
      out.println("새 리뷰를 저장했습니다.");
    } else {
      out.println("새 리뷰 저장 실패");
    }
  }
}
