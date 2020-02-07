package ekgp49.dbc.Dao;

import java.util.List;
import ekgp49.dbc.domain.Information;

public interface InformationDao {

  int insert(Information information);

  int delete(int no);

  int update(Information information);

  Information findByNo(int no);

  List<Information> findAll();

}
