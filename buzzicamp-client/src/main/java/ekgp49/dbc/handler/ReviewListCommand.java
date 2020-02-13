package ekgp49.dbc.handler;

import java.util.List;
import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.domain.Review;

public class ReviewListCommand implements Command {
  ReviewDao reviewDao;

  public ReviewListCommand(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void execute() {
    System.out.println("리뷰");
    try {
      List<Review> reviews = reviewDao.findAll();
      for (Review r : reviews) {
        System.out.printf("%d, %s, %s, %s, %s, %s, %s \n%s\n", r.getNo(), r.getCafeName(),
            r.getCustomer(), r.getStarRate(), r.getCreatedDate(), r.getTimeFormFromToday(),
            r.getViewCount(), r.getContent());
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
