package ekgp49.dbc.Dao;

import java.util.LinkedList;
import java.util.List;
import ekgp49.dbc.domain.Information;

public class InformationObjectFileDao implements InformationDao {
  List<Information> informationList = new LinkedList<>();

  public InformationObjectFileDao() {
    informationList = new LinkedList<>();
  }

  @Override
  public int insert(Information information) {
    informationList.add(information);
    return 1;
  }

  @Override
  public int delete(int no) {
    int index = indexOf(no);
    if (index > 0) {
      informationList.remove(informationList.get(index));
      return 1;
    }
    return 0;
  }

  @Override
  public int update(Information information) {
    int index = information.getNo();
    if (index > 0) {
      informationList.set(index, information);
      return 1;
    }
    return 0;
  }

  @Override
  public Information findByNo(int no) {
    int index = indexOf(no);
    if (index > 0) {
      return informationList.get(index);
    }
    return null;
  }

  @Override
  public List<Information> findAll() {
    return informationList;
  }

  private int indexOf(int no) {
    int index = -1;
    for (Information i : informationList) {
      if (i.getNo() == no) {
        index = no;
        break;
      }
    }
    if (index == -1) {
      return -1;
    }
    return index;
  }


}
