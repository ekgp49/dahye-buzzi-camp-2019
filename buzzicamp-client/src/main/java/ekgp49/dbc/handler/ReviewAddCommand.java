package ekgp49.dbc.handler;

import java.sql.Date;
import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.domain.Review;
import util.Prompt;

public class ReviewAddCommand implements Command {
  Prompt prompt;
  ReviewDao reviewDao;

  public ReviewAddCommand(Prompt prompt, ReviewDao reviewDao) {
    this.prompt = prompt;
    this.reviewDao = reviewDao;
  }

  @Override
  public void execute() {
    System.out.println("리뷰");
    Review review = new Review();
    review.setCafeName(prompt.inputString("카페 상호: "));
    review.setCustomer(prompt.inputString("고객: "));
    review.setStarRate(prompt.inputInt("별점: "));
    review.setContent(prompt.inputString("내용은: "));
    review.setCreatedDate(new Date(System.currentTimeMillis()));
    review.setTimeFormFromToday(String.format("%1$tp %1$tH:%1$tM:%1$tS ", new java.util.Date()));
    review.setViewCount(0);

    try {
      reviewDao.insert(review);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
