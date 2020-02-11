package ekgp49.dbc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.domain.Review;

public class ReviewSelectServlet implements Servlet {
  ReviewDao reviewDao;

  public ReviewSelectServlet(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    int star = in.readInt();
    Review[] arr = reviewDao.findAll().toArray(new Review[reviewDao.size()]);
    Review[] selectedArr = new Review[arr.length];
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i].getStarRate() == star) {
        Review review = arr[i];
        selectedArr[count++] = review;
      }
    }
    selectedArr = Arrays.copyOf(selectedArr, count);
    if (count != 0) {
      out.writeUTF("OK");
      out.writeObject(selectedArr);
      return;
    }
    out.writeUTF("FAIL");
    out.writeUTF("해당 리뷰가 없습니다");
  }
}
