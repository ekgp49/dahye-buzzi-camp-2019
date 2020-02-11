package ekgp49.dbc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import ekgp49.dbc.dao.InformationDao;

public class InformationDeleteServlet implements Servlet {
  InformationDao infoDao;

  public InformationDeleteServlet(InformationDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    int no = in.readInt();
    if (infoDao.findByNo(no) == null) {
      out.writeUTF("FAIL");
      out.writeUTF("해당번호의 회원정보이 없습니다.");
    } else {
      out.writeUTF("OK");
      infoDao.delete(no);
      out.writeUTF("정보를 삭제했습니다.");
    }
    out.flush();
  }
}
