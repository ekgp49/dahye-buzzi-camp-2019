package ekgp49.dbc.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ekgp49.dbc.dao.InfoMenuDao;
import ekgp49.dbc.domain.InfoMenu;
import ekgp49.dbc.domain.Information;

public class InfoMenuDaoImpl implements InfoMenuDao {
  SqlSessionFactory sqlSessionFactory;

  public InfoMenuDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Information information) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.insert("InfoMenuMapper.insertInfoMenu", information);
      return count;
    }
  }

  @Override
  public int deleteAll(int infoNo) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.delete("InfoMenuMapper.deleteInfoMenu", infoNo);
      return count;
    }
  }

  @Override
  public List<InfoMenu> findAll(int infoNo) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("InfoMenuMapper.selectInfoMenu", infoNo);
    }
  }
}
