package ekgp49.dbc.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.domain.Review;

public class ReviewDaoImpl implements ReviewDao {
  SqlSessionFactory sqlSessionFactory;

  public ReviewDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Review review) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.insert("ReviewMapper.insertReview", review);
      return count;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.delete("ReviewMapper.deleteReview", no);
      return count;
    }
  }

  @Override
  public int update(Review review) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.update("ReviewMapper.updateReview", review);
      return count;
    }
  }

  @Override
  public Review findByNo(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("ReviewMapper.selectReview", no);
    }
  }

  @Override
  public List<Review> findAll() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("ReviewMapper.selectReview");
    }
  }

  @Override
  public List<Review> selectStar(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("ReviewMapper.selectReviewByRate", no);
    }
  }
}
