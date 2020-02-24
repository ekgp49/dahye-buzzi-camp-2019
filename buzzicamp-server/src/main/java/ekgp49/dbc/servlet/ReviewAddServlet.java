package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.domain.Review;

public class ReviewAddServlet implements Servlet {
  ReviewDao reviewDao;

  public ReviewAddServlet(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    Review review = new Review();
    out.println("상호? \n!{}!");
    review.setCafeName(in.nextLine());
    out.println("고객명? \n!{}!");
    review.setCustomer(in.nextLine());
    out.println("별점? \n!{}!");
    review.setStarRate(Integer.parseInt(in.nextLine()));
    out.println("내용?? \n!{}!");
    review.setContent(in.nextLine());

    if (reviewDao.insert(review) > 0) {
      out.println("새 리뷰를 저장했습니다.");
    } else {
      out.println("새 리뷰 저장 실패");
    }
  }
}
