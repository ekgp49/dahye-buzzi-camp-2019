package ekgp49.dbc.service;

import java.util.List;
import ekgp49.dbc.domain.Review;

public interface ReviewService {
  void add(Review review) throws Exception;

  void delete(int no) throws Exception;

  int update(Review review) throws Exception;

  List<Review> list() throws Exception;

  List<Review> search(int star) throws Exception;

  Review get(int no) throws Exception;


}
