package ekgp49.dbc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.dbc.domain.Information;

public class InformationUpdateServlet implements Servlet {
  InformationDao infoDao;

  public InformationUpdateServlet(InformationDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    int no = in.readInt();
    Information info = infoDao.findByNo(no);
    if (info != null) {
      out.writeUTF("OK");
      out.flush();
      return;
    }
    out.writeUTF("FAIL");
    out.writeUTF("해당번호의 회원정보이 없습니다.");
    try {
      out.writeObject(info);
      infoDao.update((Information) in.readObject());
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF("정보 수정 실패");
    }
    out.writeUTF("OK");
    out.writeUTF("정보를 수정했습니다");
    out.flush();
  }
}
