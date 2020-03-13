package ekgp49.dbc.service.impl;

import java.util.List;
import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.domain.Review;
import ekgp49.dbc.service.ReviewService;

public class ReviewServiceImpl implements ReviewService {
  ReviewDao reviewDao;

  public ReviewServiceImpl(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void add(Review review) throws Exception {
    if (reviewDao.insert(review) > 0) {
    } else {
      throw new Exception("해당번호의 리뷰정보가 없습니다.");
    }
  }

  @Override
  public void delete(int no) throws Exception {
    if (reviewDao.delete(no) > 0) {
    } else {
      throw new Exception("해당번호의 리뷰정보가 없습니다.");
    }
  }

  @Override
  public Review get(int no) throws Exception {
    return reviewDao.findByNo(no);
  }

  @Override
  public List<Review> list() throws Exception {
    return reviewDao.findAll();
  }

  @Override
  public List<Review> search(int star) throws Exception {
    return reviewDao.selectStar(star);
  }

  @Override
  public int update(Review review) throws Exception {
    return reviewDao.update(review);
  }
}
