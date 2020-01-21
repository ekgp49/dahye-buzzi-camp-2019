package ekgp49.dbc.handler;

import java.sql.Date;
import java.util.List;
import ekgp49.dbc.domain.Review;
import util.Prompt;

public class ReviewAddCommand implements Command {
  Prompt prompt;
  List<Review> reviewList;
  private int no = 1;

  public ReviewAddCommand(Prompt prompt, List<Review> list) {
    this.prompt = prompt;
    reviewList = list;
  }

  @Override
  public void execute() {
    System.out.println("리뷰");
    Review review = new Review();
    review.setNo(this.no++);
    review.setCafeName(prompt.inputString("카페 상호: "));
    review.setCustomer(prompt.inputString("고객: "));
    review.setStarRate(prompt.inputInt("별점: "));
    review.setContent(prompt.inputString("내용은: "));
    review.setCreatedDate(new Date(System.currentTimeMillis()));
    review.setTimeFormFromToday(String.format("%1$tp %1$tH:%1$tM:%1$tS ", new java.util.Date()));
    review.setViewCount(0);

    reviewList.add(review);
    System.out.println("저장하였습니다.");
  }
}
