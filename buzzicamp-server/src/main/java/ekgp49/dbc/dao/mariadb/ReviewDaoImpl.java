package ekgp49.dbc.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.domain.Review;

public class ReviewDaoImpl implements ReviewDao {
  Connection con;

  public ReviewDaoImpl(Connection con) throws Exception {
    this.con = con;
  }

  @Override
  public int insert(Review review) throws Exception {
    Statement stmt = con.createStatement();
    return stmt.executeUpdate("insert into review(name, customer, rate, conts, vw_cnt, time_fr_td)"
        + " values('" + review.getCafeName() + "', '" + review.getCustomer() + "', "
        + review.getStarRate() + ", '" + review.getContent() + "', " + review.getViewCount() + ")");
  }

  @Override
  public int delete(int no) throws Exception {
    Statement stmt = con.createStatement();
    return stmt.executeUpdate("delete from review where review_id=" + no);
  }

  @Override
  public int update(Review review) throws Exception {
    Statement stmt = con.createStatement();
    return stmt.executeUpdate("update review set rate=" + review.getStarRate() + ", conts='"
        + review.getContent() + "' where review_id=" + review.getNo());
  }

  @Override
  public Review findByNo(int no) throws Exception {
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("select * from review where review_id=" + no);
    Review review = new Review();
    if (rs.next()) {
      review.setCafeName(rs.getString("name"));
      review.setContent(rs.getString("conts"));
      review.setCreatedDate(rs.getDate("cdt"));
      review.setCustomer(rs.getString("customer"));
      review.setNo(rs.getInt("review_id"));
      review.setStarRate(rs.getInt("rate"));
      review.setViewCount(rs.getInt("vw_cnt"));
      return review;
    } else {
      return null;
    }
  }

  @Override
  public List<Review> findAll() throws Exception {
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("select * from review");
    ArrayList<Review> list = new ArrayList<>();

    while (rs.next()) {
      Review review = new Review();
      review.setCafeName(rs.getString("name"));
      review.setContent(rs.getString("conts"));
      review.setCreatedDate(rs.getDate("cdt"));
      review.setCustomer(rs.getString("customer"));
      review.setNo(rs.getInt("review_id"));
      review.setStarRate(rs.getInt("rate"));
      review.setViewCount(rs.getInt("vw_cnt"));
      list.add(review);
    }
    return list;
  }

  @Override
  public List<Review> selectStar(int no) throws Exception {
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("select * from review where rate=" + no);
    ArrayList<Review> list = new ArrayList<>();

    while (rs.next()) {
      Review review = new Review();
      review.setCafeName(rs.getString("name"));
      review.setContent(rs.getString("conts"));
      review.setCreatedDate(rs.getDate("cdt"));
      review.setCustomer(rs.getString("customer"));
      review.setNo(rs.getInt("review_id"));
      review.setStarRate(rs.getInt("rate"));
      review.setViewCount(rs.getInt("vw_cnt"));
      list.add(review);
    }
    return list;
  }

}
