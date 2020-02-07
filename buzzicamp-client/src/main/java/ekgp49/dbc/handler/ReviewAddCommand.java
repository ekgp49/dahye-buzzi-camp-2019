package ekgp49.dbc.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import ekgp49.dbc.domain.Review;
import util.Prompt;

public class ReviewAddCommand implements Command {
  Prompt prompt;
  ObjectOutputStream out;
  ObjectInputStream in;

  public ReviewAddCommand(Prompt prompt, ObjectOutputStream out, ObjectInputStream in) {
    this.prompt = prompt;
    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {
    try {
      out.writeUTF("/review/add");
      out.flush();
      int no = in.readInt();
      System.out.println("리뷰");
      Review review = new Review();
      review.setNo(no);
      review.setCafeName(prompt.inputString("카페 상호: "));
      review.setCustomer(prompt.inputString("고객: "));
      review.setStarRate(prompt.inputInt("별점: "));
      review.setContent(prompt.inputString("내용은: "));
      review.setCreatedDate(new Date(System.currentTimeMillis()));
      review.setTimeFormFromToday(String.format("%1$tp %1$tH:%1$tM:%1$tS ", new java.util.Date()));
      review.setViewCount(0);

      out.writeObject(review);
      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      System.out.println(in.readUTF());

    } catch (Exception e) {
      System.out.println("실행 중 오류 발생" + e.getMessage());
    }
  }
}
