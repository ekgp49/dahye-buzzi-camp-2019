package ekgp49.dbc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import ekgp49.dbc.domain.Information;

public class InformationAddServlet implements Servlet {
  List<Information> informationList;

  public InformationAddServlet(List<Information> informationList) {
    this.informationList = informationList;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    int no;
    no = informationList.size() == 0 ? 1
        : informationList.get(informationList.size() - 1).getNo() + 1;
    out.writeInt(no);
    out.flush();
    informationList.add((Information) in.readObject());
    out.writeUTF("OK");
    out.writeUTF("정보를 저장했습니다.");

  }
}
