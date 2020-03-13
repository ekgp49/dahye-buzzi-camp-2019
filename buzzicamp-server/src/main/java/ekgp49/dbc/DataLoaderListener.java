package ekgp49.dbc;

import java.io.InputStream;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import ekgp49.dbc.context.ApplicationContextListener;
import ekgp49.dbc.dao.InfoMenuDao;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.dao.mariadb.InfoMenuDaoImpl;
import ekgp49.dbc.dao.mariadb.InformationDaoImpl;
import ekgp49.dbc.dao.mariadb.ReviewDaoImpl;
import ekgp49.dbc.service.impl.InformationServiceImpl;
import ekgp49.dbc.service.impl.ReviewServiceImpl;
import ekgp49.sql.PlatformTransactionManager;
import ekgp49.sql.SqlSessionFactoryProxy;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      InputStream inputStream = Resources.getResourceAsStream("ekgp49/dbc/conf/mybatis-config.xml");
      SqlSessionFactory sqlSessionFactory =
          new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder().build(inputStream));
      PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);

      context.put("sqlSessionFactory", sqlSessionFactory);
      InformationDao infoDao = new InformationDaoImpl(sqlSessionFactory);
      ReviewDao reviewDao = new ReviewDaoImpl(sqlSessionFactory);
      InfoMenuDao infoMenuDao = new InfoMenuDaoImpl(sqlSessionFactory);

      context.put("informationService",
          new InformationServiceImpl(infoDao, infoMenuDao, txManager));
      context.put("reviewService", new ReviewServiceImpl(reviewDao));


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
