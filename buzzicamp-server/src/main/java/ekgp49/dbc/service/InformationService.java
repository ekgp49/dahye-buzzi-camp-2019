package ekgp49.dbc.service;

import java.util.HashMap;
import java.util.List;
import ekgp49.dbc.domain.InfoMenu;
import ekgp49.dbc.domain.Information;

public interface InformationService {
  void add(Information information) throws Exception;

  void delete(int no) throws Exception;

  Information get(int no) throws Exception;

  List<Information> list() throws Exception;

  List<Information> search(HashMap<String, Object> params) throws Exception;

  void update(Information information, List<InfoMenu> menuList) throws Exception;
}
