package ekgp49.dbc.handler;

import java.util.List;
import ekgp49.dbc.domain.Review;
import util.Prompt;

public class ReviewUpdateCommand implements Command {
  Prompt prompt;
  List<Review> reviewList;

  public ReviewUpdateCommand(Prompt prompt, List<Review> list) {
    this.prompt = prompt;
    reviewList = list;
  }

  @Override
  public void execute() {
    int index = indexOfReview(prompt.inputInt("리뷰 번호는? "));
    if (index == -1) {
      System.out.println("해당 번호의 리뷰가 없습니다.");
      return;
    }

    Review old = this.reviewList.get(index);

    String answer = prompt.inputString("리뷰를 변경하시겠습니까? (Y/n)");
    if (!answer.equalsIgnoreCase("y")) {
      System.out.println("리뷰 변경을 취소했습니다.");
      return;
    }
    Review review = new Review();
    review.setNo(this.reviewList.get(index).getNo());
    review.setCafeName(old.getCafeName());
    review.setCustomer(old.getCustomer());
    review.setStarRate(prompt.inputInt(
        String.format("별점(%s) : ", String.valueOf(this.reviewList.get(index).getStarRate())),
        this.reviewList.get(index).getStarRate()));
    review.setContent(prompt.inputString(String.format("내용: ", old.getContent())));
    review.setCreatedDate(old.getCreatedDate());
    review.setTimeFormFromToday(old.getTimeFormFromToday());
    review.setViewCount(old.getViewCount());

    if (!review.equals(old)) {
      this.reviewList.set(index, review);
      System.out.println("리뷰를 수정했습니다.");
    } else {
      System.out.println("리뷰 변경을 취소했습니다.");
    }
  }

  private int indexOfReview(int no) {
    for (int i = 0; i < this.reviewList.size(); i++) {
      if (this.reviewList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
