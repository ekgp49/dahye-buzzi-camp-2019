package ekgp49.dbc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import ekgp49.dbc.dao.ReviewDao;

public class ReviewListServlet implements Servlet {
  ReviewDao reviewDao;

  public ReviewListServlet(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(reviewDao.findAll());
  }
}
