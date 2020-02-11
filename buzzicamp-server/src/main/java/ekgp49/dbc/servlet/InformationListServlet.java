package ekgp49.dbc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import ekgp49.dbc.dao.InformationDao;

public class InformationListServlet implements Servlet {
  InformationDao infoDao;

  public InformationListServlet(InformationDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(infoDao.findAll());
  }
}
