package ekgp49.dbc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.domain.Review;

public class ReviewUpdateServlet implements Servlet {
  ReviewDao reviewDao;

  public ReviewUpdateServlet(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    int no = in.readInt();
    if (reviewDao.findByNo(no) == null) {
      out.writeUTF("FAIL");
      out.writeUTF("해당번호의 리뷰가 없습니다.");
      return;
    }
    out.writeUTF("OK");
    out.flush();
    try {
      out.writeObject(reviewDao.findByNo(no));
      reviewDao.update((Review) in.readObject());
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF("리뷰 수정 실패");
    }
    out.writeUTF("OK");
    out.writeUTF("리뷰를 수정했습니다");
  }
}
