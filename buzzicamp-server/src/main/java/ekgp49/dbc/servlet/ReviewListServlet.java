package ekgp49.dbc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import ekgp49.dbc.domain.Review;

public class ReviewListServlet implements Servlet {
  List<Review> reviewList;

  public ReviewListServlet(List<Review> reviewList) {
    this.reviewList = reviewList;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(reviewList);
  }
}
