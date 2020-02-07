package ekgp49.dbc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import ekgp49.dbc.domain.Information;

public class InformationUpdateServlet implements Servlet {
  List<Information> informationList;

  public InformationUpdateServlet(List<Information> informationList) {
    this.informationList = informationList;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    int no = in.readInt();
    int index = -1;
    for (int i = 0; i < informationList.size(); i++) {
      if (informationList.get(i).getNo() == no) {
        index = i;
        break;
      }
    }
    if (index == -1) {
      out.writeUTF("FAIL");
      out.writeUTF("해당번호의 회원정보이 없습니다.");
      return;
    }
    out.writeUTF("OK");
    out.flush();
    try {
      out.writeObject(informationList.get(index));
      informationList.set(index, (Information) in.readObject());
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF("정보 수정 실패");
    }
    out.writeUTF("OK");
    out.writeUTF("정보를 수정했습니다");
  }
}
