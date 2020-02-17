package ekgp49.dbc.handler;

import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.domain.Review;
import util.Prompt;

public class ReviewUpdateCommand implements Command {
  Prompt prompt;
  ReviewDao reviewDao;

  public ReviewUpdateCommand(Prompt prompt, ReviewDao reviewDao) {
    this.prompt = prompt;
    this.reviewDao = reviewDao;
  }

  @Override
  public void execute() {
    int no = prompt.inputInt("리뷰 번호는? ");
    Review old;
    try {
      old = reviewDao.findByNo(no);
    } catch (Exception e) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String answer = prompt.inputString("리뷰를 변경하시겠습니까? (Y/n)");
    if (!answer.equalsIgnoreCase("y")) {
      System.out.println("리뷰 변경을 취소했습니다.");
      return;
    }
    Review review = new Review();
    review.setNo(old.getNo());
    review.setCafeName(old.getCafeName());
    review.setCustomer(old.getCustomer());
    review.setStarRate(prompt.inputInt(
        String.format("별점(%s) : ", String.valueOf(old.getStarRate())), old.getStarRate()));
    review.setContent(prompt.inputString(String.format("내용: ", old.getContent())));
    review.setCreatedDate(old.getCreatedDate());
    review.setTimeFormFromToday(old.getTimeFormFromToday());
    review.setViewCount(old.getViewCount());

    if (review.equals(old)) {
      System.out.println("리뷰 변경을 취소했습니다.");
      return;
    }
    try {
      reviewDao.update(review);
      System.out.println("리뷰를 변경했습니다.");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
