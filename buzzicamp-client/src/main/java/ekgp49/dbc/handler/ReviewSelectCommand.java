package ekgp49.dbc.handler;

import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.domain.Review;
import util.Prompt;

public class ReviewSelectCommand implements Command {
  Prompt prompt;
  ReviewDao reviewDao;

  public ReviewSelectCommand(Prompt prompt, ReviewDao reviewDao) {
    this.prompt = prompt;
    this.reviewDao = reviewDao;
  }

  @Override
  public void execute() {
    int star = prompt.inputInt("별점: ");
    try {
      Review[] arr = reviewDao.selectStar(star);
      for (Review r : arr) {
        System.out.println(r.getCafeName());
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
