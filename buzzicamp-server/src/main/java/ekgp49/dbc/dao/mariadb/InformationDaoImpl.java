package ekgp49.dbc.dao.mariadb;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.dbc.domain.Information;

public class InformationDaoImpl implements InformationDao {
  SqlSessionFactory sqlSessionFactory;

  public InformationDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Information info) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.insert("InformationMapper.insertInformation", info);
      return count;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.delete("InformationMapper.deleteInformation", no);
      return count;
    }
  }

  @Override
  public int update(Information info) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.update("InformationMapper.updateInformation", info);
      return count;
    }
  }

  @Override
  public Information findByNo(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("InformationMapper.selectInformation", no);
    }
  }

  @Override
  public List<Information> findAll() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("InformationMapper.selectInformation");
    }
  }

  @Override
  public List<Information> search(Map<String, Object> params) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("InformationMapper.searchInformation", params);
    }
  }
}
