package ekgp49.dbc.dao;

import java.util.List;
import ekgp49.dbc.domain.Review;

public interface ReviewDao {

  int insert(Review review) throws Exception;

  int delete(int no) throws Exception;

  int update(Review review) throws Exception;

  Review findByNo(int no) throws Exception;

  List<Review> findAll() throws Exception;

  int getConcreteNo() throws Exception;

  int size() throws Exception;

  Review[] selectStar(int no) throws Exception;
}
