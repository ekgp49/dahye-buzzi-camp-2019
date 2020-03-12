package ekgp49.dbc.dao;

import java.util.List;
import ekgp49.dbc.domain.InfoMenu;
import ekgp49.dbc.domain.Information;

public interface InfoMenuDao {

  int insert(Information information) throws Exception;

  int deleteAll(int infoNo) throws Exception;

  List<InfoMenu> findAll(int infoNo) throws Exception;
}
