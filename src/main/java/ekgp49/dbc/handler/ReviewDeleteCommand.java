package ekgp49.dbc.handler;

import java.util.List;
import ekgp49.dbc.domain.Review;
import util.Prompt;

public class ReviewDeleteCommand implements Command {
  Prompt prompt;
  List<Review> reviewList;

  public ReviewDeleteCommand(Prompt prompt, List<Review> list) {
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

    this.reviewList.remove(index);
    System.out.println("리뷰를 삭제했습니다.");
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
