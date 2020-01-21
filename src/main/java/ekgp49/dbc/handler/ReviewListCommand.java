package ekgp49.dbc.handler;

import java.util.Iterator;
import java.util.List;
import ekgp49.dbc.domain.Review;

public class ReviewListCommand implements Command {
  List<Review> reviewList;

  public ReviewListCommand(List<Review> list) {
    reviewList = list;
  }

  @Override
  public void execute() {
    System.out.println("리뷰");
    if (this.reviewList.size() == 0) {
      return;
    }
    Iterator<Review> item = reviewList.iterator();
    while (item.hasNext()) {
      Review r = item.next();
      System.out.printf("%d, %s, %s, %s, %s, %s, %s \n%s\n", r.getNo(), r.getCafeName(),
          r.getCustomer(), r.getStarRate(), r.getCreatedDate(), r.getTimeFormFromToday(),
          r.getViewCount(), r.getContent());
    }
  }
}
