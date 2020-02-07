package ekgp49.dbc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import ekgp49.dbc.domain.Review;

public class ReviewAddServlet implements Servlet {
  List<Review> reviewList;

  public ReviewAddServlet(List<Review> reviewList) {
    this.reviewList = reviewList;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    int no;
    no = reviewList.size() == 0 ? 1 : reviewList.get(reviewList.size() - 1).getNo() + 1;
    out.writeInt(no);
    out.flush();
    reviewList.add((Review) in.readObject());
    out.writeUTF("OK");
    out.writeUTF("정보를 저장했습니다.");
  }
}
