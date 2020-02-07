package ekgp49.dbc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import ekgp49.dbc.domain.Information;

public class InformationListServlet implements Servlet {
  List<Information> informationList;

  public InformationListServlet(List<Information> informationList) {
    this.informationList = informationList;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(informationList);
  }
}
