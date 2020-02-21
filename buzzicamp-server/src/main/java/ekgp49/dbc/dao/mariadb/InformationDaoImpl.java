package ekgp49.dbc.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.dbc.domain.Information;

public class InformationDaoImpl implements InformationDao {
  Connection con;

  public InformationDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(Information info) throws Exception {
    Statement stmt = con.createStatement();
    return stmt
        .executeUpdate("insert into information(name, addr, tel, web, op_t, cl_t, hol_day, menu)"
            + " values('" + info.getCafeName() + "', '" + info.getCafeAddress() + "', '"
            + info.getCafeCall() + "', '" + info.getCafeWebSite() + "', '" + info.getOpenTime()
            + "', '" + info.getCloseTime() + "', '" + info.getHolliday() + "', '"
            + info.getCafeMenu() + "')");
  }

  @Override
  public int delete(int no) throws Exception {
    Statement stmt = con.createStatement();
    return stmt.executeUpdate("delete from information where information_id=" + no);
  }

  @Override
  public int update(Information info) throws Exception {
    Statement stmt = con.createStatement();
    return stmt.executeUpdate("update information set name ='" + info.getCafeName() + "',"
        + " addr = '" + info.getCafeAddress() + "', tel = '" + info.getCafeCall() + "',"
        + " web = '" + info.getCafeWebSite() + "', op_t = '" + info.getOpenTime() + "',"
        + " cl_t = '" + info.getCloseTime() + "', hol_day = '" + info.getHolliday() + "',"
        + " menu = '" + info.getCafeMenu() + "' where information_id=" + info.getNo());
  }

  @Override
  public Information findByNo(int no) throws Exception {
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("select * from information where information_id=" + no);
    if (rs.next()) {
      Information info = new Information();
      info.setCafeAddress(rs.getString("addr"));
      info.setCafeCall(rs.getString("tel"));
      info.setCafeMenu(rs.getString("menu"));
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

  @Override
  public List<Information> findAll() throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from information");) {
      ArrayList<Information> list = new ArrayList<>();
      while (rs.next()) {
        Information info = new Information();
        info.setCafeAddress(rs.getString("addr"));
        info.setCafeCall(rs.getString("tel"));
        info.setCafeMenu(rs.getString("menu"));
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
  public int getConcreteNo() throws Exception {
    return 0;
  }

}
