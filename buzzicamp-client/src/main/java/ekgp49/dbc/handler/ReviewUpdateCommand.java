package ekgp49.dbc.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import ekgp49.dbc.domain.Review;
import util.Prompt;

public class ReviewUpdateCommand implements Command {
  Prompt prompt;
  ObjectOutputStream out;
  ObjectInputStream in;

  public ReviewUpdateCommand(Prompt prompt, ObjectOutputStream out, ObjectInputStream in) {
    this.prompt = prompt;
    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {
    int no = prompt.inputInt("리뷰 번호는? ");
    try {
      out.writeUTF("/review/update");
      out.writeInt(no);
      out.flush();
      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      Review old = (Review) in.readObject();

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
      out.writeObject(review);
      if (in.readUTF().equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      System.out.println(in.readUTF());
    } catch (Exception e) {
      System.out.println("실행 중 오류 발생" + e.getMessage());
    }
  }
}
