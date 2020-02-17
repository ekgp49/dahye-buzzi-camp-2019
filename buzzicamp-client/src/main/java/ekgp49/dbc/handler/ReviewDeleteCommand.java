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
      if (reviewDao.delete(no) > 0) {
        System.out.println("삭제했습니다.");
      } else {
        System.out.println("해당하는 번호의 리뷰가 없습니다.");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
