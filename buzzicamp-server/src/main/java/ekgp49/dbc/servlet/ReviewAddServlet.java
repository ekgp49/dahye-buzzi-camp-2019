package ekgp49.dbc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.domain.Review;

public class ReviewAddServlet implements Servlet {
  ReviewDao reviewDao;

  public ReviewAddServlet(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    int no = reviewDao.getConcreteNo();
    out.writeInt(no);
    out.flush();
    reviewDao.insert((Review) in.readObject());
    out.writeUTF("OK");
    out.writeUTF("정보를 저장했습니다.");
    out.flush();
  }
}
