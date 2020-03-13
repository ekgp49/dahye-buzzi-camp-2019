package ekgp49.dbc.service.impl;

import java.util.HashMap;
import java.util.List;
import ekgp49.dbc.dao.InfoMenuDao;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.dbc.domain.InfoMenu;
import ekgp49.dbc.domain.Information;
import ekgp49.dbc.service.InformationService;
import ekgp49.sql.PlatformTransactionManager;
import ekgp49.sql.TransactionTemplate;

public class InformationServiceImpl implements InformationService {
  InformationDao infoDao;
  InfoMenuDao infoMenuDao;
  TransactionTemplate transactionTemplate;

  public InformationServiceImpl(InformationDao infoDao, InfoMenuDao infoMenuDao,
      PlatformTransactionManager txManager) {
    this.infoDao = infoDao;
    this.infoMenuDao = infoMenuDao;
    this.transactionTemplate = new TransactionTemplate(txManager);
  }


  @Override
  public void add(Information info) throws Exception {
    transactionTemplate.execute(() -> {
      if (infoDao.insert(info) > 0) {
        infoMenuDao.insert(info);
      }
      return null;
    });
  }

  @Override
  public void delete(int no) throws Exception {
    transactionTemplate.execute(() -> {
      infoMenuDao.deleteAll(no);
      if (infoDao.delete(no) > 0) {
      } else {
        throw new Exception("번호가 유효하지 않습니다.");
      }
      return null;
    });
  }

  @Override
  public Information get(int no) throws Exception {
    return infoDao.findByNo(no);
  }

  @Override
  public List<Information> list() throws Exception {
    return infoDao.findAll();
  }

  @Override
  public List<Information> search(HashMap<String, Object> params) throws Exception {
    return infoDao.search(params);
  }

  @Override
  public void update(Information information, List<InfoMenu> menuList) throws Exception {
    transactionTemplate.execute(() -> {
      if (menuList != null) {
        infoMenuDao.deleteAll(information.getNo());
        information.setMenuList(menuList);
      }

      infoMenuDao.insert(information);

      if (infoDao.update(information) > 0) {
      } else {
        throw new Exception("정보 수정 실패");
      }
      return null;
    });
  }
}
