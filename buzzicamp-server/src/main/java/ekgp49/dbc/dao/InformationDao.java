package ekgp49.dbc.dao;

import java.util.List;
import java.util.Map;
import ekgp49.dbc.domain.Information;

public interface InformationDao {

  int insert(Information information) throws Exception;

  int delete(int no) throws Exception;

  int update(Information information) throws Exception;

  Information findByNo(int no) throws Exception;

  List<Information> findAll() throws Exception;

  List<Information> search(Map<String, Object> params) throws Exception;
}
