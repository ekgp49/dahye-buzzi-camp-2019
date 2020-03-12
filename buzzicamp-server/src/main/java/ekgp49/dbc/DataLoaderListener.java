package ekgp49.dbc;

import java.io.InputStream;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import ekgp49.dbc.context.ApplicationContextListener;
import ekgp49.dbc.dao.mariadb.InfoMenuDaoImpl;
import ekgp49.dbc.dao.mariadb.InformationDaoImpl;
import ekgp49.dbc.dao.mariadb.ReviewDaoImpl;
import ekgp49.sql.PlatformTransactionManager;
import ekgp49.sql.SqlSessionFactoryProxy;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      InputStream inputStream = Resources.getResourceAsStream("ekgp49/dbc/conf/mybatis-config.xml");
      SqlSessionFactory sqlSessionFactory =
          new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder().build(inputStream));

      context.put("sqlSessionFactory", sqlSessionFactory);

      context.put("infoDao", new InformationDaoImpl(sqlSessionFactory));
      context.put("reviewDao", new ReviewDaoImpl(sqlSessionFactory));
      context.put("infoMenuDao", new InfoMenuDaoImpl(sqlSessionFactory));

      context.put("transactionManager", new PlatformTransactionManager(sqlSessionFactory));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    try {
      System.out.println("데이터를 저장합니다.");
    } catch (Exception e) {
    }
  }
}
