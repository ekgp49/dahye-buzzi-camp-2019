package ekgp49.dbc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import ekgp49.dbc.domain.Review;

public class ReviewUpdateServlet implements Servlet {
  List<Review> reviewList;

  public ReviewUpdateServlet(List<Review> reviewList) {
    this.reviewList = reviewList;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    int no = in.readInt();
    int index = -1;
    for (int i = 0; i < reviewList.size(); i++) {
      if (reviewList.get(i).getNo() == no) {
        index = i;
        break;
      }
    }
    if (index == -1) {
      out.writeUTF("FAIL");
      out.writeUTF("해당번호의 리뷰가 없습니다.");
      return;
    }
    out.writeUTF("OK");
    out.flush();
    try {
      out.writeObject(reviewList.get(index));
      reviewList.set(index, (Review) in.readObject());
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF("리뷰 수정 실패");
    }
    out.writeUTF("OK");
    out.writeUTF("리뷰를 수정했습니다");
  }
}
