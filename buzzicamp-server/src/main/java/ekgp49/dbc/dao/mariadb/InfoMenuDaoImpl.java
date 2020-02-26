package ekgp49.dbc.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ekgp49.dbc.dao.InfoMenuDao;
import ekgp49.dbc.domain.InfoMenu;
import util.ConnectionFactory;

public class InfoMenuDaoImpl implements InfoMenuDao {
  ConnectionFactory conFactory;

  public InfoMenuDaoImpl(ConnectionFactory conFactory) throws Exception {
    this.conFactory = conFactory;
  }

  @Override
  public int insert(InfoMenu menu) throws Exception {
    try (Connection con = conFactory.getConnection(); Statement stmt = con.createStatement()) {
      return stmt.executeUpdate("insert into info_menu(menu_name, information_id) values('"
          + menu.getName() + "', '" + menu.getInformationNo() + "')");
    }
  }

  @Override
  public int deleteAll(int infoNo) throws Exception {
    try (Connection con = conFactory.getConnection(); Statement stmt = con.createStatement()) {
      return stmt.executeUpdate("delete from info_menu where information_id=" + infoNo);
    }
  }

  @Override
  public List<InfoMenu> findAll(int infoNo) throws Exception {
    try (Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from info_menu")) {
      ArrayList<InfoMenu> list = new ArrayList<>();
      while (rs.next()) {
        InfoMenu menu = new InfoMenu();
        menu.setInformationNo(rs.getInt("information_id"));
        menu.setName(rs.getString("menu_name"));
        list.add(menu);
      }
      return list;
    }
  }
}
