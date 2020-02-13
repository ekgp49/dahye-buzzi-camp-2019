package ekgp49.dbc.handler;

import java.util.List;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.dbc.domain.Information;

public class InformationListCommand implements Command {
  InformationDao infoDao;

  public InformationListCommand(InformationDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void execute() {
    List<Information> infos = null;
    try {
      infos = infoDao.findAll();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    for (Information info : infos) {
      System.out.printf("%d, %s, %s, %s, %s, %s ~ %s, %s, %s\n", info.getNo(), info.getCafeName(),
          info.getCafeAddress(), info.getCafeCall(), info.getCafeWebSite(), info.getOpenTime(),
          info.getCloseTime(), info.getHolliday(), info.getCafeMenu());
    }
  }
}


