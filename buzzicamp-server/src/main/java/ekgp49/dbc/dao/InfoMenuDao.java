package ekgp49.dbc.dao;

import java.util.List;
import ekgp49.dbc.domain.InfoMenu;

public interface InfoMenuDao {

  int insert(InfoMenu menu) throws Exception;

  int deleteAll(int infoNo) throws Exception;

  //
  // int update(InfoMenu menu) throws Exception;
  //
  // InfoMenu findByNo(int no) throws Exception;
  //
  List<InfoMenu> findAll(int infoNo) throws Exception;
}
