package ekgp49.dbc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import ekgp49.dbc.domain.Review;

public class ReviewSelectServlet implements Servlet {
  List<Review> reviewList;

  public ReviewSelectServlet(List<Review> reviewList) {
    this.reviewList = reviewList;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    int star = in.readInt();
    Review[] arr = new Review[reviewList.size()];
    int count = 0;
    for (int i = 0; i < reviewList.size(); i++) {
      if (reviewList.get(i).getStarRate() == star) {
        Review review = reviewList.get(i);
        arr[count++] = review;
      }
    }
    arr = Arrays.copyOf(arr, count);
    if (count == 0) {
      out.writeUTF("FAIL");
      out.writeUTF("해당 리뷰가 없습니다");
      return;
    }
    out.writeUTF("OK");
    out.writeObject(arr);
  }
}
