package ekgp49.sql;

public class TransactionTemplate {
  PlatformTransactionManager txManager;

  public TransactionTemplate(PlatformTransactionManager txManager) {
    this.txManager = txManager;
  }

  public Object execute(TransactionCallback callback) throws Exception {
    txManager.beginTransaction();
    try {
      Object result = null;
      callback.doInTransaction();
      txManager.commit();
      return result;
    } catch (Exception e) {
      txManager.rollback();
      throw e; // 새로 new Exception() 해줄 필요 없음.
    }
  }
}
