package ekgp49.dbc.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.domain.Review;

public class ReviewDaoProxy implements ReviewDao {
  ObjectOutputStream out;
  ObjectInputStream in;

  public ReviewDaoProxy(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @Override
  public int insert(Review review) throws Exception {
    out.writeUTF("/review/add");
    out.flush();
    int no = in.readInt();
    review.setNo(no);
    out.writeObject(review);
    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    System.out.println(in.readUTF());
    return 1;
  }

  @Override
  public int delete(int no) throws Exception {
    out.writeUTF("/review/delete");
    out.writeInt(no);
    out.flush();
    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    System.out.println(in.readUTF());
    return 1;
  }

  @Override
  public int update(Review review) throws Exception {
    out.writeObject(review);
    if (in.readUTF().equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    System.out.println(in.readUTF());
    return 1;
  }

  @Override
  public Review findByNo(int no) throws Exception {
    out.writeUTF("/review/update");
    out.writeInt(no);
    out.flush();
    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return (Review) in.readObject();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Review> findAll() throws Exception {
    out.writeUTF("/review/list");
    out.flush();
    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return (List<Review>) in.readObject();
  }

  @Override
  public int getConcreteNo() throws Exception {
    return -1;
  }

  @Override
  public int size() throws Exception {
    return -1;
  }

  @Override
  public Review[] selectStar(int no) throws Exception {
    out.writeUTF("/review/star");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return (Review[]) in.readObject();
  }
}
