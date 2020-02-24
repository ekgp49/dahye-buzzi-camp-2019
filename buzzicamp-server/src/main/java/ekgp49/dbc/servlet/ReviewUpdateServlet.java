package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.domain.Review;

public class ReviewUpdateServlet implements Servlet {
  ReviewDao reviewDao;

  public ReviewUpdateServlet(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    out.println("번호? \n!{}!");
    int no = Integer.parseInt(in.nextLine());
    Review old = reviewDao.findByNo(no);
    if (old == null) {
      out.println("해당번호의 리뷰가 없습니다.");
      return;
    }
    Review review = new Review();
    review.setNo(old.getNo());
    out.println("별점(" + old.getStarRate() + ")? \n!{}!");
    review.setStarRate(Integer.parseInt(in.nextLine()));
    out.println("내용(" + old.getContent() + ")? \n!{}!");
    review.setContent(in.nextLine());

    if (old.getStarRate() == review.getStarRate() && old.getContent().equals(review.getContent())) {
      out.println("리뷰 변경을 취소합니다.");
    }
    if (reviewDao.update(review) > 0) {
      out.println("리뷰를 변경했습니다.");
    } else {
      out.println("리뷰 변경 실패");
    }
  }
}
