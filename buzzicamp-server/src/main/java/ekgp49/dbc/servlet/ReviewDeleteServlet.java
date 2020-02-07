package ekgp49.dbc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import ekgp49.dbc.domain.Review;

public class ReviewDeleteServlet implements Servlet {
  List<Review> reviewList;

  public ReviewDeleteServlet(List<Review> reviewList) {
    this.reviewList = reviewList;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    int no;
    int index;
    no = in.readInt();
    index = -1;
    for (int i = 0; i < reviewList.size(); i++) {
      if (reviewList.get(i).getNo() == no) {
        index = i;
        break;
      }
    }
    if (index == -1) {
      out.writeUTF("FAIL");
      out.writeUTF("해당번호의 리뷰정보가 없습니다.");
    } else {
      out.writeUTF("OK");
      reviewList.remove(index);
      out.writeUTF("리뷰를 삭제했습니다.");
    }
  }
}
