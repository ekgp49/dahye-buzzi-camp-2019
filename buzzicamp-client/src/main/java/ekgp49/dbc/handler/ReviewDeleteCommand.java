package ekgp49.dbc.handler;

import ekgp49.dbc.dao.ReviewDao;
import util.Prompt;

public class ReviewDeleteCommand implements Command {
  Prompt prompt;
  ReviewDao reviewDao;

  public ReviewDeleteCommand(Prompt prompt, ReviewDao reviewDao) {
    this.prompt = prompt;
    this.reviewDao = reviewDao;
  }

  @Override
  public void execute() {
    int no = prompt.inputInt("리뷰 번호는 ");
    try {
      reviewDao.delete(no);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
