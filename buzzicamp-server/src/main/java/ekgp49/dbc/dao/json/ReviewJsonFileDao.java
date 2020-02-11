package ekgp49.dbc.dao.json;

import java.util.List;
import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.domain.Review;

public class ReviewJsonFileDao extends AbstractJsonFileDao<Review> implements ReviewDao {

  public ReviewJsonFileDao(String filename) {
    super(filename);
  }

  @Override
  public int insert(Review review) throws Exception {
    list.add(review);
    saveData();
    return 1;
  }

  @Override
  public int delete(int no) throws Exception {
    int index = indexOf(no);
    if (index > 0) {
      list.remove(list.get(index));
      saveData();
      return 1;
    }
    return 0;
  }

  @Override
  public int update(Review review) throws Exception {
    int index = indexOf(review.getNo());
    if (index > 0) {
      list.set(index, review);
      saveData();
      return 1;
    }
    return 0;
  }

  @Override
  public Review findByNo(int no) throws Exception {
    int index = indexOf(no);
    if (index > 0) {
      return list.get(index);
    }
    return null;
  }

  @Override
  public List<Review> findAll() throws Exception {
    return list;
  }

  @Override
  protected <K> int indexOf(K key) throws Exception {
    int index = -1;
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == (int) key) {
        index = i;
        break;
      }
    }
    if (index == -1) {
      return -1;
    }
    return index;
  }

  @Override
  public int getConcreteNo() throws Exception {
    int no = list.size() == 0 ? 1 : list.get(list.size() - 1).getNo() + 1;
    return no;
  }

  @Override
  public int size() throws Exception {
    return super.size();
  }
}
