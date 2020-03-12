package ekgp49.sql;

import java.sql.Connection;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

public class SqlSessionFactoryProxy implements SqlSessionFactory {
  SqlSessionFactory originalFactory;

  // SqlSession을 스레드에 보관할 저장소를 준비한다.
  ThreadLocal<SqlSession> sqlSessionLocal = new ThreadLocal<>();

  public SqlSessionFactoryProxy(SqlSessionFactory originalFactory) {
    this.originalFactory = originalFactory;
  }

  // 스레드 작업이 끝난 후 sqlSession 객체를 제거하고 닫는다.
  public void closeSession() {
    SqlSession sqlSession = sqlSessionLocal.get();
    if (sqlSession != null) {
      sqlSessionLocal.remove();
      ((SqlSessionProxy) sqlSession).realclose();
    }
  }

  @Override
  public SqlSession openSession() {
    // 기본으로 자동 커밋하는 sqlSession을 만든다.
    return this.openSession(true);
  }

  @Override
  public SqlSession openSession(boolean autoCommit) {
    // 스레드에 보관된 것을 꺼낸다.
    SqlSession sqlSession = sqlSessionLocal.get();
    if (sqlSession == null) {
      // 보관된게 없으면 자동 커밋으로 동작하는 SqlSession 객체를 만든다.
      sqlSession = new SqlSessionProxy(originalFactory.openSession(autoCommit));
      sqlSessionLocal.set(sqlSession);
    }
    return sqlSession;
  }

  @Override
  public SqlSession openSession(Connection connection) {
    return originalFactory.openSession(connection);
  }

  @Override
  public SqlSession openSession(TransactionIsolationLevel level) {
    return originalFactory.openSession(level);
  }

  @Override
  public SqlSession openSession(ExecutorType execType) {
    return originalFactory.openSession(execType);
  }

  @Override
  public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
    return originalFactory.openSession(execType, autoCommit);
  }

  @Override
  public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
    return originalFactory.openSession(execType, level);
  }

  @Override
  public SqlSession openSession(ExecutorType execType, Connection connection) {
    return originalFactory.openSession(execType, connection);
  }

  @Override
  public Configuration getConfiguration() {
    return originalFactory.getConfiguration();
  }


}
