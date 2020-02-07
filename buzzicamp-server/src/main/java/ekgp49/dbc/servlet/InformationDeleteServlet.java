package ekgp49.dbc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import ekgp49.dbc.domain.Information;

public class InformationDeleteServlet implements Servlet {
  List<Information> informationList;

  public InformationDeleteServlet(List<Information> informationList) {
    this.informationList = informationList;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    int no;
    int index;
    no = in.readInt();
    index = -1;
    for (int i = 0; i < informationList.size(); i++) {
      if (informationList.get(i).getNo() == no) {
        index = i;
        break;
      }
    }
    if (index == -1) {
      out.writeUTF("FAIL");
      out.writeUTF("해당번호의 회원정보이 없습니다.");
    } else {
      out.writeUTF("OK");
      informationList.remove(index);
      out.writeUTF("정보를 삭제했습니다.");
    }
  }
}
