package ekgp49.dbc.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.dbc.domain.Information;
import util.ConnectionFactory;

public class InformationDaoImpl implements InformationDao {
  ConnectionFactory conFactory;

  public InformationDaoImpl(ConnectionFactory conFactory) throws Exception {
    this.conFactory = conFactory;
  }

  @Override
  public int insert(Information info) throws Exception {
    try (Connection con = conFactory.getConnection(); Statement stmt = con.createStatement()) {
      int result = stmt.executeUpdate(
          "insert into information(name, addr, tel, web, op_t, cl_t, hol_day)" + " values('"
              + info.getCafeName() + "', '" + info.getCafeAddress() + "', '" + info.getCafeCall()
              + "', '" + info.getCafeWebSite() + "', '" + info.getOpenTime() + "', '"
              + info.getCloseTime() + "', '" + info.getHolliday() + "')",
          Statement.RETURN_GENERATED_KEYS);

      try (ResultSet generatedKeySet = stmt.getGeneratedKeys()) {
        generatedKeySet.next(); // 값 가져오기
        info.setNo(generatedKeySet.getInt(1)); // 값 꺼내기
      }

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Connection con = conFactory.getConnection(); Statement stmt = con.createStatement();) {
      return stmt.executeUpdate("delete from information where information_id=" + no);
    }
  }

  @Override
  public int update(Information info) throws Exception {
    try (Connection con = conFactory.getConnection(); Statement stmt = con.createStatement()) {
      return stmt.executeUpdate("update information set name ='" + info.getCafeName() + "',"
          + " addr = '" + info.getCafeAddress() + "', tel = '" + info.getCafeCall() + "',"
          + " web = '" + info.getCafeWebSite() + "', op_t = '" + info.getOpenTime() + "',"
          + " cl_t = '" + info.getCloseTime() + "', hol_day = '" + info.getHolliday() + "'"
          + " where information_id=" + info.getNo());
    }
  }

  @Override
  public Information findByNo(int no) throws Exception {
    try (Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from information where information_id=" + no)) {
      if (rs.next()) {
        Information info = new Information();
        info.setCafeAddress(rs.getString("addr"));
        info.setCafeCall(rs.getString("tel"));
        info.setCafeName(rs.getString("name"));
        info.setCafeWebSite(rs.getString("web"));
        info.setCloseTime(rs.getString("cl_t"));
        info.setHolliday(rs.getString("hol_day"));
        info.setNo(rs.getInt("information_id"));
        info.setOpenTime(rs.getString("op_t"));
        return info;
      } else {
        return null;
      }
    }
  }

  @Override
  public List<Information> findAll() throws Exception {
    try (Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from information")) {
      ArrayList<Information> list = new ArrayList<>();
      while (rs.next()) {
        Information info = new Information();
        info.setCafeAddress(rs.getString("addr"));
        info.setCafeCall(rs.getString("tel"));
        info.setCafeName(rs.getString("name"));
        info.setCafeWebSite(rs.getString("web"));
        info.setCloseTime(rs.getString("cl_t"));
        info.setHolliday(rs.getString("hol_day"));
        info.setNo(rs.getInt("information_id"));
        info.setOpenTime(rs.getString("op_t"));
        list.add(info);
      }
      return list;
    }
  }

  @Override
  public List<Information> search(String keyword) throws Exception {
    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select i.information_id, i.name, i.addr, i.tel, i.web, i.op_t, i.cl_t, i.hol_day"
                + " from information i, info_menu m"
                + " where i.name like concat ('%', ?, '%') or i.addr like concat ('%', ?, '%')"
                + " or m.menu_name like concat ('%', ?, '%')")) {

      stmt.setString(1, keyword);
      stmt.setString(2, keyword);
      stmt.setString(3, keyword);

      ResultSet rs = stmt.executeQuery();
      ArrayList<Information> list = new ArrayList<>();
      while (rs.next()) {
        Information info = new Information();
        info.setCafeAddress(rs.getString("addr"));
        info.setCafeCall(rs.getString("tel"));
        info.setCafeName(rs.getString("name"));
        info.setCafeWebSite(rs.getString("web"));
        info.setCloseTime(rs.getString("cl_t"));
        info.setHolliday(rs.getString("hol_day"));
        info.setNo(rs.getInt("information_id"));
        info.setOpenTime(rs.getString("op_t"));
        list.add(info);
      }
      rs.close();
      return list;
    }
  }
}
