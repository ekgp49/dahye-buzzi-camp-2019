package ekgp49.dbc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.dbc.domain.Information;

public class InformationAddServlet implements Servlet {
  InformationDao infoDao;

  public InformationAddServlet(InformationDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    int no = infoDao.getConcreteNo();
    out.writeInt(no);
    out.flush();
    infoDao.insert((Information) in.readObject());
    out.writeUTF("OK");
    out.writeUTF("정보를 저장했습니다.");
    out.flush();

  }
}
