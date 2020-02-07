package ekgp49.dbc.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import ekgp49.dbc.domain.Review;

public class ReviewListCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;

  public ReviewListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {
    System.out.println("리뷰");
    try {
      out.writeUTF("/review/list");
      out.flush();
      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      List<Review> reviews = (List<Review>) in.readObject();
      for (Review r : reviews) {
        System.out.printf("%d, %s, %s, %s, %s, %s, %s \n%s\n", r.getNo(), r.getCafeName(),
            r.getCustomer(), r.getStarRate(), r.getCreatedDate(), r.getTimeFormFromToday(),
            r.getViewCount(), r.getContent());
      }
    } catch (Exception e) {
      System.out.println("실행 중 오류 발생" + e.getMessage());
    }
  }
}
