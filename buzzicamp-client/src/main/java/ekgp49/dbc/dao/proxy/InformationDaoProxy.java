package ekgp49.dbc.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.dbc.domain.Information;

public class InformationDaoProxy implements InformationDao {
  DaoProxyHelper daoProxyHelper;

  public InformationDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  @Override
  public int insert(Information information) throws Exception {
    return (int) daoProxyHelper.request(new Worker() {
      @Override
      public Object execute(ObjectOutputStream out, ObjectInputStream in) throws Exception {
        out.writeUTF("/info/add");
        out.flush();
        int no = in.readInt();
        information.setNo(no);
        out.writeObject(information);
        if (in.readUTF().equals("FAIL")) {
          throw new Exception(in.readUTF());
        }
        System.out.println(in.readUTF());
        return 1;
      }
    });
  }

  @Override
  public int delete(int no) throws Exception {
    return (int) daoProxyHelper.request((out, in) -> {
      out.writeUTF("/info/delete");
      out.writeInt(no);
      out.flush();
      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      System.out.println(in.readUTF());
      return 1;
    });
  }

  @Override
  public int update(Information information) throws Exception {
    return (int) daoProxyHelper.request((out, in) -> {
      out.writeObject(information);
      if (in.readUTF().equals("FAIL")) {
        System.out.println(in.readUTF());
        throw new Exception(in.readUTF());
      }
      System.out.println(in.readUTF());
      return 1;
    });
  }

  @Override
  public Information findByNo(int no) throws Exception {
    return (Information) daoProxyHelper.request((out, in) -> {
      out.writeUTF("/info/update");
      out.writeInt(no);
      out.flush();
      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return (Information) in.readObject();
    });
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Information> findAll() throws Exception {
    return (List<Information>) daoProxyHelper.request((out, in) -> {
      out.writeUTF("/info/list");
      out.flush();
      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return (List<Information>) in.readObject();
    });
  }

  @Override
  public int getConcreteNo() throws Exception {
    return -1;
  }
}
