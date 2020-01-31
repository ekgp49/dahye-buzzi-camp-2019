package ekgp49.dbc.handler;

import java.util.Iterator;
import java.util.List;
import ekgp49.dbc.domain.Information;

public class InformationListCommand implements Command {
  List<Information> informationList;

  public InformationListCommand(List<Information> list) {
    this.informationList = list;
  }

  @Override
  public void execute() {
    if (this.informationList.size() == 0) {
      return;
    }
    Iterator<Information> item = informationList.iterator();
    while (item.hasNext()) {
      Information info = item.next();
      System.out.printf("%d, %s, %s, %s, %s, %s ~ %s, %s, %s\n", info.getNo(), info.getCafeName(),
          info.getCafeAddress(), info.getCafeCall(), info.getCafeWebSite(), info.getOpenTime(),
          info.getCloseTime(), info.getHolliday(), info.getCafeMenu());
    }
  }

}
