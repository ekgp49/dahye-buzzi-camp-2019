package ekgp49.dbc.handler;

import java.util.Arrays;
import java.util.List;
import ekgp49.dbc.domain.Review;
import util.Prompt;

public class ReviewSelectCommand implements Command {
  Prompt prompt;
  List<Review> reviewList;

  public ReviewSelectCommand(Prompt prompt, List<Review> list) {
    this.prompt = prompt;
    reviewList = list;
  }

  @Override
  public void execute() {
    int star = prompt.inputInt("별점: ");
    Review[] arr = new Review[this.reviewList.size()];
    int count = 0;
    for (int i = 0; i < this.reviewList.size(); i++) {
      if (this.reviewList.get(i).getStarRate() == star) {
        Review review = this.reviewList.get(i);
        arr[count++] = review;
      }
    }
    arr = Arrays.copyOf(arr, count);

    for (Review r : arr) {
      System.out.println(r.getCafeName());
    }
  }
}
