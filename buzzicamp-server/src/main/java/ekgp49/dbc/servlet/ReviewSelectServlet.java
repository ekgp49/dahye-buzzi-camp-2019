package ekgp49.dbc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    Review[] selectedArr = reviewDao.selectStar(star);
    if (selectedArr.length == 0) {
      out.writeUTF("FAIL");
      out.writeUTF("해당 리뷰가 없습니다");
      return;
    }
    out.writeUTF("OK");
    out.writeObject(selectedArr);
  }
}
