package ekgp49.dbc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import ekgp49.dbc.dao.ReviewDao;

public class ReviewDeleteServlet implements Servlet {
  ReviewDao reviewDao;

  public ReviewDeleteServlet(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    int no = in.readInt();
    if (reviewDao.findByNo(no) == null) {
      out.writeUTF("FAIL");
      out.writeUTF("해당번호의 리뷰정보가 없습니다.");
    } else {
      out.writeUTF("OK");
      reviewDao.delete(no);
      out.writeUTF("리뷰를 삭제했습니다.");
      out.flush();
    }
  }
}
